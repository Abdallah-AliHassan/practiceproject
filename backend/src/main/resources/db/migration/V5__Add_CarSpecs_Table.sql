CREATE TABLE carspecs(
                         id BIGSERIAL PRIMARY KEY,
                         name TEXT NOT NULL,
                         year INTEGER NOT NULL,
                         factory_id BIGINT NOT NULL
);