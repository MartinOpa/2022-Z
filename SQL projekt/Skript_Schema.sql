drop table if exists complaint;
drop table if exists reply;
drop table if exists review;
drop table if exists reservation;
drop table if exists reservationtimes;
drop table if exists vehicle;
drop table if exists "user";

CREATE TABLE complaint (
    reservation_id INTEGER NOT NULL,
    complaint      NVARCHAR(300) NOT NULL,
    resolved       CHAR(1) NOT NULL,
    complaintdate  DATE NOT NULL
)
GO

ALTER TABLE complaint ADD CONSTRAINT complaint_pk PRIMARY KEY CLUSTERED ( reservation_id )
     WITH (
     ALLOW_PAGE_LOCKS = ON ,
     ALLOW_ROW_LOCKS = ON )
     ON "default"
GO

CREATE TABLE reply (
    reply                 NVARCHAR(300) NOT NULL,
    replydate             DATETIME NOT NULL,
    review_reservation_id INTEGER NOT NULL
)
GO

ALTER TABLE reply ADD CONSTRAINT reply_pk PRIMARY KEY CLUSTERED ( review_reservation_id )
     WITH (
     ALLOW_PAGE_LOCKS = ON ,
     ALLOW_ROW_LOCKS = ON )
     ON "default"
GO

CREATE TABLE reservation (
    id              INTEGER NOT NULL,
    rt_datetime     DATETIME NOT NULL,
    user_id         INTEGER NOT NULL,
    vehicle_vin     VARCHAR(30) NOT NULL,
    task            NVARCHAR(30) NOT NULL,
    taskdescription NVARCHAR(100),
    apxduration     DATETIME,
    useremail       NVARCHAR(40) NOT NULL,
    discount        DECIMAL(2, 1),
    bill            INTEGER NOT NULL,
    payment         INTEGER NOT NULL,
    paid            CHAR(1) NOT NULL
)
GO

ALTER TABLE reservation ADD CONSTRAINT reservation_pk PRIMARY KEY CLUSTERED ( id )
     WITH (
     ALLOW_PAGE_LOCKS = ON ,
     ALLOW_ROW_LOCKS = ON )
     ON "default"
GO

CREATE TABLE reservationtimes (
    datetime DATETIME NOT NULL,
    capacity INTEGER NOT NULL
)
GO

ALTER TABLE reservationtimes ADD CONSTRAINT reservationtimes_pk PRIMARY KEY CLUSTERED ( datetime )
     WITH (
     ALLOW_PAGE_LOCKS = ON ,
     ALLOW_ROW_LOCKS = ON )
     ON "default"
GO

CREATE TABLE review (
    reservation_id INTEGER NOT NULL,
    user_id        INTEGER NOT NULL,
    userfirstname  NVARCHAR(20) NOT NULL,
    reviewdate     DATETIME NOT NULL,
    lastedit       DATETIME,
    reviewpts      INTEGER NOT NULL,
    reviewtext     NVARCHAR(300)
)
GO

ALTER TABLE review ADD CONSTRAINT review_pk PRIMARY KEY CLUSTERED ( reservation_id )
     WITH (
     ALLOW_PAGE_LOCKS = ON ,
     ALLOW_ROW_LOCKS = ON )
     ON "default"
GO

CREATE TABLE "user" (
    id          INTEGER NOT NULL,
    login       NVARCHAR(10) NOT NULL,
    firstname   NVARCHAR(20) NOT NULL,
    lastname    NVARCHAR(20) NOT NULL,
    phonenumber INTEGER,
    address     NVARCHAR(60),
    email       NVARCHAR(40) NOT NULL,
    type        INTEGER NOT NULL,
    partnerdate DATE
)
GO

ALTER TABLE "user" ADD CONSTRAINT user_pk PRIMARY KEY CLUSTERED ( id )
     WITH (
     ALLOW_PAGE_LOCKS = ON ,
     ALLOW_ROW_LOCKS = ON )
     ON "default"
GO

CREATE TABLE vehicle (
    vin          VARCHAR(30) NOT NULL,
    user_id      INTEGER NOT NULL,
    licenseplate VARCHAR(10) NOT NULL,
    manufacturer NVARCHAR(20) NOT NULL,
    model        NVARCHAR(20) NOT NULL,
    year         INTEGER NOT NULL,
    chassis      VARCHAR(10)
)
GO

ALTER TABLE vehicle ADD CONSTRAINT vehicle_pk PRIMARY KEY CLUSTERED ( vin )
     WITH (
     ALLOW_PAGE_LOCKS = ON ,
     ALLOW_ROW_LOCKS = ON )
     ON "default"
GO

ALTER TABLE complaint
    ADD CONSTRAINT complaint_reservation_fk FOREIGN KEY ( reservation_id )
        REFERENCES reservation ( id )
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
GO

ALTER TABLE reply
    ADD CONSTRAINT reply_review_fk FOREIGN KEY ( review_reservation_id )
        REFERENCES review ( reservation_id )
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
GO

ALTER TABLE reservation
    ADD CONSTRAINT reservation_rt_fk FOREIGN KEY ( rt_datetime )
        REFERENCES reservationtimes ( datetime )
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
GO

ALTER TABLE reservation
    ADD CONSTRAINT reservation_user_fk FOREIGN KEY ( user_id )
        REFERENCES "User" ( id )
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
GO

ALTER TABLE reservation
    ADD CONSTRAINT reservation_vehicle_fk FOREIGN KEY ( vehicle_vin )
        REFERENCES vehicle ( vin )
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
GO

ALTER TABLE review
    ADD CONSTRAINT review_reservation_fk FOREIGN KEY ( reservation_id )
        REFERENCES reservation ( id )
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
GO

ALTER TABLE review
    ADD CONSTRAINT review_user_fk FOREIGN KEY ( user_id )
        REFERENCES "User" ( id )
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
GO

ALTER TABLE vehicle
    ADD CONSTRAINT vehicle_user_fk FOREIGN KEY ( user_id )
        REFERENCES "User" ( id )
        ON DELETE NO ACTION
        ON UPDATE NO ACTION

ALTER TABLE "user"
    WITH CHECK ADD CONSTRAINT CheckType
    CHECK ("type" <= 3 AND "type" >= 0 )

ALTER TABLE vehicle
    WITH CHECK ADD CONSTRAINT CheckPlateFormat
    CHECK (licenseplate not like '%[^0-9A-Z]%')

ALTER TABLE review
    WITH CHECK ADD CONSTRAINT CheckPts
    ChECK (reviewpts >= 0 AND reviewpts <= 10)

ALTER TABLE reservation
    WITH CHECK ADD CONSTRAINT CheckPayment
    CHECK (payment <= 3 AND payment >= 0)

ALTER TABLE reservation
    WITH CHECK ADD CONSTRAINT CheckDiscount
    CHECK (discount IS NULL OR discount >= 0.5 OR discount <= 7.5)