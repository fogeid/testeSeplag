CREATE SCHEMA IF NOT EXISTS public;

CREATE TABLE tb_cidade (cid_id SERIAL PRIMARY KEY, cid_nome VARCHAR(200) NOT NULL, cid_uf CHAR(2) NOT NULL);

CREATE TABLE tb_endereco (end_id SERIAL PRIMARY KEY, end_tipo_logradouro VARCHAR(50) NOT NULL, end_logradouro VARCHAR(200) NOT NULL,end_numero INT, end_bairro VARCHAR(100), cid_id INT NOT NULL,FOREIGN KEY (cid_id) REFERENCES tb_cidade (cid_id));

CREATE TABLE tb_pessoa (pes_id SERIAL PRIMARY KEY,pes_nome VARCHAR(200) NOT NULL,pes_data_nascimento DATE,pes_sexo VARCHAR(20), pes_mae VARCHAR(200), pes_pai VARCHAR(200));

CREATE TABLE tb_pessoa_endereco (pes_id INT NOT NULL, end_id INT NOT NULL, PRIMARY KEY (pes_id, end_id), FOREIGN KEY (pes_id) REFERENCES tb_pessoa (pes_id), FOREIGN KEY (end_id) REFERENCES tb_endereco (end_id));

CREATE TABLE tb_unidade (unid_id SERIAL PRIMARY KEY, unid_nome VARCHAR(200) NOT NULL, unid_sigla VARCHAR(20) NOT NULL);

CREATE TABLE tb_unidade_endereco (unid_id INT NOT NULL, end_id INT NOT NULL, PRIMARY KEY (unid_id, end_id), FOREIGN KEY (unid_id) REFERENCES tb_unidade (unid_id), FOREIGN KEY (end_id) REFERENCES tb_endereco (end_id));

CREATE TABLE tb_lotacao (lot_id SERIAL PRIMARY KEY, pes_id INT NOT NULL, unid_id INT NOT NULL, lot_data_lotacao DATE NOT NULL, lot_data_remocao DATE, FOREIGN KEY (pes_id) REFERENCES tb_pessoa (pes_id),FOREIGN KEY (unid_id) REFERENCES tb_unidade (unid_id));

CREATE TABLE tb_servidor_efetivo (pes_id INT PRIMARY KEY, sef_matricula VARCHAR(20) NOT NULL UNIQUE, FOREIGN KEY (pes_id) REFERENCES tb_pessoa (pes_id));

CREATE TABLE tb_servidor_temporario (pes_id INT PRIMARY KEY, st_data_admissao DATE NOT NULL, st_data_demissao DATE, FOREIGN KEY (pes_id) REFERENCES tb_pessoa (pes_id));

CREATE TABLE tb_foto_pessoa (fot_id SERIAL PRIMARY KEY, pes_id INT NOT NULL, fot_data DATE NOT NULL, fot_bucket VARCHAR(50) NOT NULL, fot_hash VARCHAR(50) NOT NULL, FOREIGN KEY (pes_id) REFERENCES tb_pessoa (pes_id));