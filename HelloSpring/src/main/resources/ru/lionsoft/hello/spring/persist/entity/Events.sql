/**
 * Author:  Igor Morenko <morenko at lionsoft.ru>
 * Created: 6 дек. 2019 г.
 */

CREATE TABLE EVENTS
(
    EVENT_ID    BIGINT NOT NULL 
            GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    EVENT_DATE  DATE,
    TITLE       VARCHAR(80),
    CONSTRAINT PK_EVENTS PRIMARY KEY (EVENT_ID)
);

INSERT INTO EVENTS (EVENT_ID, EVENT_DATE, TITLE) VALUES (default, '2019-12-06', 'Hello Hibernate');
