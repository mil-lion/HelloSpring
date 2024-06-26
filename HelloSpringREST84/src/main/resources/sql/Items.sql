/**
 * Author:  Igor Morenko <morenko at lionsoft.ru>
 * Created: 6 дек. 2019 г.
 */

drop table items;
CREATE TABLE ITEMS
(
    ITEM_ID       BIGINT NOT NULL 
            GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    NUM           VARCHAR(80) NOT NULL,
    TITLE         VARCHAR(80),
    ARTIST        VARCHAR(80),
    RELEASE_DATE  DATE,
    LIST_PRICE    DECIMAL(12, 2),
    PRICE         DECIMAL(12, 2),
    CONSTRAINT PK_ITEMS PRIMARY KEY (ITEM_ID)
);

INSERT INTO ITEMS (NUM, TITLE, ARTIST, RELEASE_DATE, LIST_PRICE, PRICE) 
    VALUES  ('CD501', 'Diva', 'Annie Lennox', '1992-01-04', 17.97, 13.99),
            ('CD502', 'Dream of the Blue Turtles', 'Sting', '1985-02-05', 17.97, 14.99),
            ('CD503', 'Trouble is...', 'Kenny Wayne Shepherd Band', '1997-08-08', 17.97, 14.99),
            ('CD504', 'Lie to Me', 'Jonny Lang', '1997-08-26', 17.97, 14.99),
            ('CD505', 'Little Earthquakes', 'Tori Amos', '1992-01-18', 17.97, 14.99),
            ('CD506', 'Seal', 'Seal', '1991-08-18', 17.97, 14.99),
            ('CD507', 'Ian Moore', 'Ian Moore', '1993-12-05', 9.97, 9.97),
            ('CD508', 'So Much for the Afterglow', 'Everclear', '1997-01-19', 16.97, 13.99),
            ('CD509', 'Surfacing', 'Sarah McLachlan', '1997-12-04', 17.97, 13.99),
            ('CD510', 'Hysteria', 'Def Leppard', '1987-06-20', 17.97, 14.99),
            ('CD511', 'A Life of Saturdays', 'Dexter Freebish', '2000-12-06', 16.97, 12.99),
            ('CD512', 'Human Clay', 'Creed', '1999-10-21', 18.97, 13.28),
            ('CD513', 'My, I''m Large', 'Bobs', '1987-02-20', 11.97, 11.97),
            ('CD514', 'So', 'Peter Gabriel', '1986-10-03', 17.97, 13.99),
            ('CD515', 'Big Ones', 'Aerosmith', '1994-05-08', 18.97, 14.99),
            ('CD516', '90125', 'Yes', '1983-10-16', 11.97, 11.97),
            ('CD517', '1984', 'Van Halen', '1984-08-19', 11.97, 11.97),
            ('CD518', 'Escape', 'Journey', '1981-02-25', 11.97, 11.97);

select * from items;
delete from items;
