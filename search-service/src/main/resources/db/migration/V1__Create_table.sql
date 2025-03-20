CREATE TABLE search_results
(
    id    bigint IDENTITY (1, 1) NOT NULL,
    title varchar(255),
    link  varchar(255),
    date  date,
    CONSTRAINT pk_search_results PRIMARY KEY (id)
)
    GO