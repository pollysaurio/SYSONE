INSERT INTO TRANSACCION (TIPO, TRANSACCION_DATE, IMPORTE_A_LA_FECHA) 
VALUES ('CONSULTA', CURRENT_TIMESTAMP, 150000);

INSERT INTO OPCION (DESCRIPCION, CODE, UPDATED_DATE, COSTO)
VALUES ('AIRBAG', 'AB', CURRENT_TIMESTAMP, 7000); 
INSERT INTO OPCION (DESCRIPCION, CODE, UPDATED_DATE, COSTO)
VALUES ('TECHO CORREDIZO', 'TC', CURRENT_TIMESTAMP, 12000); 
INSERT INTO OPCION (DESCRIPCION, CODE, UPDATED_DATE, COSTO)
VALUES ('ABS', 'ABS', CURRENT_TIMESTAMP, 14000); 
INSERT INTO OPCION (DESCRIPCION, CODE, UPDATED_DATE, COSTO)
VALUES ('AIRE ACONDICIONADO', 'AA', CURRENT_TIMESTAMP, 20000); 
INSERT INTO OPCION (DESCRIPCION, CODE, UPDATED_DATE, COSTO)
VALUES ('LLANTAS DE ALEACION', 'LL', CURRENT_TIMESTAMP, 12000); 

INSERT INTO TRA_OPC (ID_TRANSACCION, ID_OPCION)
VALUES (1, 2);
INSERT INTO TRA_OPC (ID_TRANSACCION, ID_OPCION)
VALUES (1, 3);
INSERT INTO TRA_OPC (ID_TRANSACCION, ID_OPCION)
VALUES (1, 5);
