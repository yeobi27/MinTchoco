-- 생성자 Oracle SQL Developer Data Modeler 18.4.0.339.1532
--   위치:        2019-05-11 03:01:19 KST
--   사이트:      Oracle Database 11g
--   유형:      Oracle Database 11g



CREATE TABLE b_product (
    pno        NUMBER NOT NULL,
    pname      VARCHAR2(10),
    price      NUMBER,
    category   VARCHAR2(10),
    detail     VARCHAR2(128),
    image      BLOB
);

ALTER TABLE b_product ADD CONSTRAINT b_product_pk PRIMARY KEY ( pno );

ALTER TABLE b_product ADD CONSTRAINT b_prod_pname_un UNIQUE ( pname );

CREATE TABLE b_stock (
    pno           NUMBER NOT NULL,
    pname         VARCHAR2(10),
    inven         NUMBER,
    exp_date      DATE,
    restck_date   DATE
);

ALTER TABLE b_stock ADD CONSTRAINT b_stock_pname_un UNIQUE ( pname );

CREATE TABLE "order" (
    ordnum       NUMBER NOT NULL,
    "date"       DATE,
    tno          NUMBER,
    pno          NUMBER,
    pname        VARCHAR2(10),
    price        NUMBER,
    quantity     NUMBER,
    tot_amount   NUMBER
);

ALTER TABLE "order" ADD CONSTRAINT order_pk PRIMARY KEY ( ordnum );

CREATE TABLE payment (
    cardyn         CHAR(1),
    cardlist       VARCHAR2(15),
    received_amo   NUMBER,
    to_rec_amo     NUMBER,
    installment    NUMBER
);

CREATE TABLE supplier (
    supno     NUMBER,
    supname   VARCHAR2(10),
    mng       VARCHAR2(10),
    tel       VARCHAR2(15),
    suploc    VARCHAR2(15),
    suplink   VARCHAR2(15)
);

ALTER TABLE b_stock
    ADD CONSTRAINT b_stck_prod_fk FOREIGN KEY ( pno )
        REFERENCES b_product ( pno );



-- Oracle SQL Developer Data Modeler 요약 보고서: 
-- 
-- CREATE TABLE                             5
-- CREATE INDEX                             0
-- ALTER TABLE                              5
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
