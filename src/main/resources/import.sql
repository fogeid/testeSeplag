-- Inserir 1 cidade (Cuiabá)
INSERT INTO tb_cidade (cid_nome, cid_uf) VALUES ('Cuiabá', 'MT');

-- Inserir 10 endereços em Cuiabá
INSERT INTO tb_endereco (end_tipo_logradouro, end_logradouro, end_numero, end_bairro, cid_id) VALUES ('Rua', 'Rua 10', '100', 'Centro', 1), ('Rua', 'Avenida Getúlio Vargas', '200', 'Quilombo', 1), ('Rua', 'Rua 24 de Outubro', '300', 'Goiabeiras', 1), ('Rua', 'Avenida Miguel Sutil', '400', 'Santa Rosa', 1), ('Rua', 'Rua Barão de Melgaço', '500', 'Porto', 1), ('Rua', 'Avenida Isaac Póvoas', '600', 'Popular', 1), ('Rua', 'Rua 13 de Junho', '700', 'Dom Aquino', 1), ('Rua', 'Avenida Fernando Corrêa', '800', 'Coxipó', 1), ('Rua', 'Rua Presidente Marques', '900', 'Jardim das Américas', 1), ('Rua', 'Avenida Historiador Rubens de Mendonça', '1000', 'Bosque da Saúde', 1);

-- Inserir 5 unidades
INSERT INTO tb_unidade (unid_nome, unid_sigla) VALUES ('Secretaria de Planejamento e Gestão', 'SEPLAG'), ('Secretaria de Educação', 'SEDUC'), ('Secretaria de Saúde', 'SES'), ('Secretaria de Segurança Pública', 'SESP'), ('Secretaria de Infraestrutura', 'SINFRA');

-- Inserir 5 unidades_endereço
INSERT INTO tb_unidade_endereco (end_id, unid_id) VALUES (1, 1);
INSERT INTO tb_unidade_endereco (end_id, unid_id) VALUES (2, 2);
INSERT INTO tb_unidade_endereco (end_id, unid_id) VALUES (3, 3);
INSERT INTO tb_unidade_endereco (end_id, unid_id) VALUES (4, 4);
INSERT INTO tb_unidade_endereco (end_id, unid_id) VALUES (5, 5);


-- Inserir 10 pessoas, cada uma com um endereço e uma lotação
-- Pessoa 1
INSERT INTO tb_pessoa (pes_nome, pes_data_nascimento, pes_sexo, pes_mae, pes_pai) VALUES ('Ana Silva', '1985-03-15', 'F', 'Maria Silva', 'José Silva');
INSERT INTO tb_pessoa_endereco (pes_id, end_id) VALUES (1, 1);
INSERT INTO tb_foto_pessoa (fp_bucket, fp_hash, pes_id) VALUES ('fotos-pessoas', 'abc123', 1);
INSERT INTO tb_lotacao (lot_data_lotacao, lot_data_remocao, lot_portaria, pes_id, unid_id) VALUES ('2023-01-01', NULL, 'PORT-001', 1, 1);
INSERT INTO tb_servidor_temporario (pes_id, st_data_admissao, st_data_demissao) VALUES (1, '2023-01-01', '2023-12-31');

-- Pessoa 2
INSERT INTO tb_pessoa (pes_nome, pes_data_nascimento, pes_sexo, pes_mae, pes_pai) VALUES ('Bruno Oliveira', '1990-07-22', 'M', 'Clara Oliveira', 'Pedro Oliveira');
INSERT INTO tb_pessoa_endereco (pes_id, end_id) VALUES (2, 2);
INSERT INTO tb_foto_pessoa (fp_bucket, fp_hash, pes_id) VALUES ('fotos-pessoas', 'def456', 2);
INSERT INTO tb_lotacao (lot_data_lotacao, lot_data_remocao, lot_portaria, pes_id, unid_id) VALUES ('2022-05-01', NULL, 'PORT-002', 2, 2);
INSERT INTO tb_servidor_efetivo (se_matricula, pes_id) VALUES ('MAT-001', 2);

-- Pessoa 3
INSERT INTO tb_pessoa (pes_nome, pes_data_nascimento, pes_sexo, pes_mae, pes_pai) VALUES ('Carla Souza', '1988-11-10', 'F', 'Ana Souza', 'Carlos Souza');
INSERT INTO tb_pessoa_endereco (pes_id, end_id) VALUES (3, 3);
INSERT INTO tb_foto_pessoa (fp_bucket, fp_hash, pes_id) VALUES ('fotos-pessoas', 'ghi789', 3);
INSERT INTO tb_lotacao (lot_data_lotacao, lot_data_remocao, lot_portaria, pes_id, unid_id) VALUES ('2021-09-01', NULL, 'PORT-003', 3, 3);
INSERT INTO tb_servidor_temporario (st_data_admissao, st_data_demissao, pes_id) VALUES ('2021-09-01', '2022-08-31', 3);

-- Pessoa 4
INSERT INTO tb_pessoa (pes_nome, pes_data_nascimento, pes_sexo, pes_mae, pes_pai) VALUES ('Diego Santos', '1995-02-18', 'M', 'Fernanda Santos', 'Marcos Santos');
INSERT INTO tb_pessoa_endereco (pes_id, end_id) VALUES (4, 4);
INSERT INTO tb_foto_pessoa (fp_bucket, fp_hash, pes_id) VALUES ('fotos-pessoas', 'jkl012', 4);
INSERT INTO tb_lotacao (lot_data_lotacao, lot_data_remocao, lot_portaria, pes_id, unid_id) VALUES ('2023-03-01', NULL, 'PORT-004', 4, 4);
INSERT INTO tb_servidor_efetivo (se_matricula, pes_id) VALUES ('MAT-002', 4);

-- Pessoa 5
INSERT INTO tb_pessoa (pes_nome, pes_data_nascimento, pes_sexo, pes_mae, pes_pai) VALUES ('Elisa Costa', '1983-06-25', 'F', 'Luciana Costa', 'Roberto Costa');
INSERT INTO tb_pessoa_endereco (pes_id, end_id) VALUES (5, 5);
INSERT INTO tb_foto_pessoa (fp_bucket, fp_hash, pes_id) VALUES ('fotos-pessoas', 'mno345', 5);
INSERT INTO tb_lotacao (lot_data_lotacao, lot_data_remocao, lot_portaria, pes_id, unid_id) VALUES ('2020-11-01', NULL, 'PORT-005', 5, 5);
INSERT INTO tb_servidor_temporario (st_data_admissao, st_data_demissao, pes_id) VALUES ('2020-11-01', '2021-10-31', 5);

-- Pessoa 6
INSERT INTO tb_pessoa (pes_nome, pes_data_nascimento, pes_sexo, pes_mae, pes_pai) VALUES ('Felipe Almeida', '1992-09-12', 'M', 'Sônia Almeida', 'João Almeida');
INSERT INTO tb_pessoa_endereco (pes_id, end_id) VALUES (6, 6);
INSERT INTO tb_foto_pessoa (fp_bucket, fp_hash, pes_id) VALUES ('fotos-pessoas', 'pqr678', 6);
INSERT INTO tb_lotacao (lot_data_lotacao, lot_data_remocao, lot_portaria, pes_id, unid_id) VALUES ('2022-07-01', NULL, 'PORT-006', 6, 1);
INSERT INTO tb_servidor_efetivo (se_matricula, pes_id) VALUES ('MAT-003', 6);

-- Pessoa 7
INSERT INTO tb_pessoa (pes_nome, pes_data_nascimento, pes_sexo, pes_mae, pes_pai) VALUES ('Gabriela Lima', '1987-04-30', 'F', 'Patrícia Lima', 'Eduardo Lima');
INSERT INTO tb_pessoa_endereco (pes_id, end_id) VALUES (7, 7);
INSERT INTO tb_foto_pessoa (fp_bucket, fp_hash, pes_id) VALUES ('fotos-pessoas', 'stu901', 7);
INSERT INTO tb_lotacao (lot_data_lotacao, lot_data_remocao, lot_portaria, pes_id, unid_id) VALUES ('2021-02-01', NULL, 'PORT-007', 7, 2);
INSERT INTO tb_servidor_temporario (st_data_admissao, st_data_demissao, pes_id) VALUES ('2021-02-01', '2022-01-31', 7);

-- Pessoa 8
INSERT INTO tb_pessoa (pes_nome, pes_data_nascimento, pes_sexo, pes_mae, pes_pai) VALUES ('Henrique Pereira', '1998-12-05', 'M', 'Juliana Pereira', 'Ricardo Pereira');
INSERT INTO tb_pessoa_endereco (pes_id, end_id) VALUES (8, 8);
INSERT INTO tb_foto_pessoa (fp_bucket, fp_hash, pes_id) VALUES ('fotos-pessoas', 'vwx234', 8);
INSERT INTO tb_lotacao (lot_data_lotacao, lot_data_remocao, lot_portaria, pes_id, unid_id) VALUES ('2023-06-01', NULL, 'PORT-008', 8, 3);
INSERT INTO tb_servidor_efetivo (se_matricula, pes_id) VALUES ('MAT-004', 8);

-- Pessoa 9
INSERT INTO tb_pessoa (pes_nome, pes_data_nascimento, pes_sexo, pes_mae, pes_pai) VALUES ('Isabela Rocha', '1984-08-20', 'F', 'Mônica Rocha', 'Antônio Rocha');
INSERT INTO tb_pessoa_endereco (pes_id, end_id) VALUES (9, 9);
INSERT INTO tb_foto_pessoa (fp_bucket, fp_hash, pes_id) VALUES ('fotos-pessoas', 'yzx567', 9);
INSERT INTO tb_lotacao (lot_data_lotacao, lot_data_remocao, lot_portaria, pes_id, unid_id) VALUES ('2020-04-01', NULL, 'PORT-009', 9, 4);
INSERT INTO tb_servidor_temporario (st_data_admissao, st_data_demissao, pes_id) VALUES ('2020-04-01', '2021-03-31', 9);

-- Pessoa 10
INSERT INTO tb_pessoa (pes_nome, pes_data_nascimento, pes_sexo, pes_mae, pes_pai) VALUES ('João Mendes', '1993-01-08', 'M', 'Teresa Mendes', 'Luiz Mendes');
INSERT INTO tb_pessoa_endereco (pes_id, end_id) VALUES (10, 10);
INSERT INTO tb_foto_pessoa (fp_bucket, fp_hash, pes_id) VALUES ('fotos-pessoas', 'abc890', 10);
INSERT INTO tb_lotacao (lot_data_lotacao, lot_data_remocao, lot_portaria, pes_id, unid_id) VALUES ('2022-10-01', NULL, 'PORT-010', 10, 5);
INSERT INTO tb_servidor_efetivo (se_matricula, pes_id) VALUES ('MAT-005', 10);