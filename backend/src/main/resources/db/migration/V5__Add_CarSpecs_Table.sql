CREATE TABLE carspecs(
                         id BIGSERIAL PRIMARY KEY,
                         name TEXT NOT NULL,
                         year TEXT NOT NULL,
                         factory_id BIGINT NOT NULL
);