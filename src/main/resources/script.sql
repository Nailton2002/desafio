create table correntista(
	id BIGSERIAL PRIMARY KEY NOT NULL,
	nome VARCHAR(200)  NOT NULL,
	cpf VARCHAR(14)  NOT NULL,
	dataNascimento date  NOT NULL
);
INSERT INTO correntista (nome, cpf, dataNascimento) VALUES ('teste correntista', '056.456.789-37', '2020-07-30');
SELECT * from correntista;

create table agencia(
	id BIGSERIAL PRIMARY KEY NOT NULL,
	nome VARCHAR(200)  NOT NULL,
	endereco varchar(500) NOT NULL
);
INSERT INTO agencia (nome, endereco) VALUES ('teste agencia', 'rua teste');
SELECT * from agencia;

create table contaCorrente(
	id BIGSERIAL PRIMARY KEY NOT NULL,
   	id_Correntista int REFERENCES correntista(id),
   	id_Agencia int REFERENCES agencia(id),
	limite DECIMAL(18,2),
	saldo DECIMAL(18,2),
	ativa char(1) CHECK (ativa IN ('T','F'))
);

UPDATE contacorrente SET id_correntista = 1 WHERE id = 8;
SELECT * from contaCorrente;
UPDATE contacorrente SET id_Agencia = 1 WHERE id = 6;
SELECT * from contaCorrente;
INSERT INTO contaCorrente (id_Correntista, id_Agencia, limite, saldo, ativa) VALUES (1, 1, 200, 400, 'T');

//este comando retorna a conta completa
SELECT * FROM correntista c INNER JOIN contaCorrente e ON c.id = e.id_Correntista;




