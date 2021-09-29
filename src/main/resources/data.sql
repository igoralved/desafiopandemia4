

CREATE TABLE ETAPA(id integer PRIMARY KEY, numero integer, descricao varchar(40));

INSERT INTO ETAPA(id, numero, descricao) VALUES(1, 1, 'Concluída');

CREATE TABLE TESTE(id integer PRIMARY KEY, numero integer, resultado varchar(40));

INSERT INTO TESTE(id, numero, resultado) VALUES(2, 2, 'Saudável');

CREATE TABLE ATENDIMENTO(id integer PRIMARY KEY, descricao varchar(40), sem_possibilidade_contagio boolean, relacionado_com_pandemia boolean, tempo_atendimento integer, data date, etapas integer, testes integer);

ALTER TABLE ATENDIMENTO
ADD CONSTRAINT etapas FOREIGN KEY (etapas) REFERENCES ETAPA(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE ATENDIMENTO
ADD CONSTRAINT testes FOREIGN KEY (testes) REFERENCES TESTE(id) ON DELETE CASCADE ON UPDATE CASCADE;

INSERT INTO ATENDIMENTO(id, descricao, sem_possibilidade_contagio, relacionado_com_pandemia, tempo_atendimento, data, etapas, testes) VALUES(3, 'ótimo', true, true, 11, '1993-08-18', 1, 2);

CREATE TABLE UNIDADE_SAUDE(id integer PRIMARY KEY, nome varchar(40), numero_pacientes integer, data date, atendimentos integer);

ALTER TABLE UNIDADE_SAUDE
ADD CONSTRAINT atendimentos FOREIGN KEY (atendimentos) REFERENCES ATENDIMENTO(id) ON DELETE CASCADE ON UPDATE CASCADE;

INSERT INTO UNIDADE_SAUDE(id, nome, numero_pacientes, data, atendimentos) VALUES(4, 'MOINHOS', 10, '1993-08-18', SELECT id FROM ATENDIMENTO WHERE id > 0);