package com.search.service.service.impl;

import com.search.service.config.SearchServiceConfig;
import com.search.service.dto.SearchResultsDto;
import com.search.service.dto.SearchResultsListDto;
import com.search.service.entity.SearchResult;
import com.search.service.entity.SearchResultMongoDb;
import com.search.service.entity.TxtFile;
import com.search.service.exception.InputValidationException;
import com.search.service.exception.NoConnectionException;
import com.search.service.exception.NoSearchResultException;
import com.search.service.mapper.SearchResultsDtoToSearchResultMapper;
import com.search.service.mapper.SearchResultsDtoToSearchResultMongoDbMapper;
import com.search.service.repository.SearchMongoRepository;
import com.search.service.repository.SearchRepository;
import com.search.service.repository.TxtFileRepository;
import com.search.service.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Norpix on 03.11.2024.
 * Description: Implementation of the {@link SearchService} interface, providing methods to perform search operations
 * and interact with the database. This service connects to an external Google-Service API to retrieve search results,
 * validates inputs, and manages database records.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final SearchServiceConfig searchServiceConfig;
    private final RestTemplate restTemplate;
    private final SearchRepository searchRepository;
    private final TxtFileRepository txtFileRepository;
    private final SearchMongoRepository searchMongoRepository;
    @Qualifier("searchResultsDtoToSearchResultMapperImpl")
    private final SearchResultsDtoToSearchResultMapper mapper;
    @Qualifier("searchResultsDtoToSearchResultMongoDbMapperImpl")
    private final SearchResultsDtoToSearchResultMongoDbMapper mongoDbMapper;

    @Override
    public List<SearchResultsDto> search(String query) throws InputValidationException {
        return getSearchResults(query);
    }

    @Override
    public List<SearchResultsDto> kafkaSearch(String query) throws InputValidationException {
        return getSearchResults(query);
    }

    private List<SearchResultsDto> getSearchResults(String query) throws InputValidationException {
        if (query.isBlank() || query.length() < 2 || query.length() > 50) {
            throw new InputValidationException();
        }
        try {
            ResponseEntity<SearchResultsListDto> response = restTemplate.getForEntity(searchServiceConfig.getBaseUrl()
                    + searchServiceConfig.getApiPath() + query, SearchResultsListDto.class);

            List<SearchResult> searchResults = response.getBody().getSearchResultsList().stream()
                    .map(mapper::map)
                    .toList();
            searchRepository.saveAll(searchResults);
            List<SearchResultMongoDb> resultMongoDb = response.getBody().getSearchResultsList().stream()
                    .map(mongoDbMapper::map)
                    .toList();

            byte[] generateTxt = generateTxt(resultMongoDb.toString());
            saveTxt(generateTxt);

            searchMongoRepository.saveAll(resultMongoDb);

            return response.getBody().getSearchResultsList().stream()
                    .map(item -> new SearchResultsDto(item.getTitle(), item.getLink()))
                    .toList();
        } catch (RestClientException restClientException) {
            throw new NoConnectionException(restClientException);
        }
    }

//    private GenericRecord convertToAvro(String schemaPath, SearchResult searchResult) throws IOException {
//        Schema schema = new Schema.Parser().setValidateDefaults(true).parse(new ClassPathResource(schemaPath).getFile());
//        AvroSchema avroSchema = new AvroSchema(schema);
//        final byte[] bytes = objectWriter.w
//    }

    private void saveTxt(byte[] txtFile) {
        TxtFile txtContent = new TxtFile();
        txtContent.setFileName("newTxt.txt");
        txtContent.setFileContent(new Binary(txtFile));
        txtFileRepository.save(txtContent);
    }

    private byte[] generateTxt(String txtFile) {
        try {
            FileWriter fileWriter = new FileWriter("filename.txt");
            fileWriter.write(txtFile);
            fileWriter.close();
            log.info("Txt generated successfully");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return txtFile.getBytes();
    }

    @Override
    public SearchResult saveResultInDatabase(SearchResult searchResult) {
        searchResult.setDateTime(mapper.currentTime());
        return searchRepository.save(searchResult);
    }

    @Override
    public SearchResult getResultFromDatabase(Long id) {
        if (searchRepository.findById(id).isPresent()) {
            return searchRepository.findById(id).get();
        } else {
            throw new NoSearchResultException(id);
        }
    }

    @Override
    public List<SearchResult> getLastFiftyResultsFromDatabase() {
        return searchRepository.findTop50ByOrderByIdDesc();
    }

    @Override
    public void deleteByIdFromDatabase(Long id) {
        if (searchRepository.findById(id).isPresent()) {
            searchRepository.deleteById(id);
        } else {
            throw new NoSearchResultException(id);
        }
    }

}
