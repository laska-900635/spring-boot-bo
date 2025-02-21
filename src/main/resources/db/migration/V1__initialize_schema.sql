CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE master_user (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    full_name VARCHAR(255) NOT NULL
);

CREATE TABLE master_account (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id UUID NOT NULL,
    balance DOUBLE PRECISION NOT NULL,
    CONSTRAINT fk_master_account_user FOREIGN KEY (user_id) REFERENCES master_user(id) ON DELETE CASCADE
);
