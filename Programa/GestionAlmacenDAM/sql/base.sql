DROP TABLE IF EXISTS Article;
CREATE TABLE IF NOT EXISTS "Article" (
                                         "PIC" TEXT  NOT NULL,
                                         "article" TEXT NOT NULL,
                                         "description" TEXT NOT NULL,
                                         "location" TEXT NOT NULL,
                                         "price" REAL NOT NULL,
                                         "stock" INTEGER NOT NULL,
                                         "isActive" BOOLEAN NOT NULL,
                                         PRIMARY KEY("PIC"));

DROP TABLE IF EXISTS CUSTOMER;
CREATE TABLE IF NOT EXISTS "Customer" (
                                          "CIC" TEXT  NOT NULL,
                                          "name" TEXT NOT NULL,
                                          "surname" TEXT NOT NULL,
                                          "cif" TEXT NOT NULL,
                                          "direction" TEXT NOT NULL,
                                          "telephoneNumber" String NOT NULL,
                                          "email" TEXT NOT NULL,
                                          "photo" TEXT NOT NULL,
                                          "createdAt" TEXT NOT NULL,
                                          PRIMARY KEY("CIC"));

DROP TABLE IF EXISTS EMPLOYEE;
CREATE TABLE IF NOT EXISTS "Employee" (
                                          "EIC" TEXT  NOT NULL,
                                          "name" TEXT NOT NULL,
                                          "surname" TEXT NOT NULL,
                                          "nif" TEXT NOT NULL,
                                          "email" TEXT NOT NULL,
                                          "photo" TEXT NOT NULL,
                                          "idUser" INTERGER NOT NULL,
                                          "isManager" BOOLEAN NOT NULL,
                                          "createdAt" TEXT NOT NULL,
                                          PRIMARY KEY("EIC"));

DROP TABLE IF EXISTS LINEORDER;
CREATE TABLE IF NOT EXISTS "LineOrder" (
                                           "OLIC" TEXT  NOT NULL,
                                           "articleº" TEXT NOT NULL,
                                           "load" INTEGER NOT NULL,
                                           "unitPrice" REAL NOT NULL,
                                           "totalPrice" REAL NOT NULL,
                                           "BelongsOrder" TEXT NOT NULL,
                                           PRIMARY KEY("OLIC"),
    FOREIGN KEY ("BelongsOrder") REFERENCES "Order" ("OIC")
    );

DROP TABLE IF EXISTS LINERECEPTION;
CREATE TABLE IF NOT EXISTS "LineReception" (
                                               "RLIC" TEXT  NOT NULL,
                                               "articleº" TEXT NOT NULL,
                                               "load" INTEGER NOT NULL,
                                               "unitPrice" REAL NOT NULL,
                                               "totalPrice" REAL NOT NULL,
                                               "BelongsReception" TEXT NOT NULL,
                                               PRIMARY KEY("RLIC"),
    FOREIGN KEY("BelongsReception") REFERENCES Reception ("RIC")
    );

DROP TABLE IF EXISTS "Order";
CREATE TABLE IF NOT EXISTS "Order" (
                                       "OIC" TEXT  NOT NULL,
                                       "Customer" TEXT NOT NULL,
                                       "Price" REAL NOT NULL,
                                       "Pay" String NOT NULL,
                                       PRIMARY KEY("OIC"));

DROP TABLE IF EXISTS "Reception";
CREATE TABLE IF NOT EXISTS "Reception" (
                                           "RIC" TEXT  NOT NULL,
                                           "Supplier" TEXT NOT NULL,
                                           "Carrier" TEXT NOT NULL,
                                           "Cost" REAL NOT NULL,
                                           PRIMARY KEY("RIC"));

DROP TABLE IF EXISTS SUPPLIER;
CREATE TABLE IF NOT EXISTS "Supplier" (
                                          "SIC" TEXT  NOT NULL,
                                          "nameSupplier" TEXT NOT NULL,
                                          "direction" TEXT NOT NULL,
                                          "telephoneNumber" TEXT NOT NULL,
                                          "email" TEXT NOT NULL,
                                          PRIMARY KEY("SIC"));

DROP TABLE IF EXISTS USER;
CREATE TABLE IF NOT EXISTS "User" (
                                      "idUser" INTERGER  NOT NULL,
                                      "username" TEXT NOT NULL,
                                      "password" TEXT NOT NULL,
                                      PRIMARY KEY("idUser"),
    FOREIGN KEY ("idUser") REFERENCES "Employee"("idUser") ON DELETE CASCADE
    );