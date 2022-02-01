CREATE TABLE audit_history
(
    id                  BIGINT       NOT NULL,
    drone_serial_number VARCHAR(255) NOT NULL,
    battery             INT          NOT NULL,
    time_stamp          datetime     NOT NULL,
    CONSTRAINT pk_audit_history PRIMARY KEY (id)
);

CREATE TABLE drone
(
    id            INT          NOT NULL,
    serial_number VARCHAR(100) NOT NULL,
    model         VARCHAR(255) NOT NULL,
    weight_limit  INT          NOT NULL,
    battery       INT          NOT NULL,
    state         VARCHAR(255) NOT NULL,
    CONSTRAINT pk_drone PRIMARY KEY (id)
);

CREATE TABLE medication
(
    id     INT          NOT NULL,
    name   VARCHAR(255) NULL,
    weight INT          NULL,
    code   VARCHAR(255) NULL,
    image  VARCHAR(255) NULL,
    CONSTRAINT pk_medication PRIMARY KEY (id)
);

CREATE TABLE drone_medication
(
    id            INT    NOT NULL,
    drone_id      INT    NULL,
    medication_id INT    NULL,
    active        BIT(1) NULL,
    CONSTRAINT pk_drone_medication PRIMARY KEY (id)
);

ALTER TABLE drone_medication
    ADD CONSTRAINT FK_DRONE_MEDICATION_ON_DRONE FOREIGN KEY (drone_id) REFERENCES drone (id);

ALTER TABLE drone_medication
    ADD CONSTRAINT FK_DRONE_MEDICATION_ON_MEDICATION FOREIGN KEY (medication_id) REFERENCES medication (id);

--sequences
CREATE SEQUENCE IF NOT EXISTS drone_med_seq
    INCREMENT 5;

CREATE SEQUENCE IF NOT EXISTS drone_id_generator
    INCREMENT 5;

CREATE SEQUENCE IF NOT EXISTS med_id_generator
    INCREMENT 5;

CREATE SEQUENCE IF NOT EXISTS audit_id_generator
    INCREMENT 5;
