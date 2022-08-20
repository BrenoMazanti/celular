CREATE TABLE tb_adm(
	id SERIAL,
	dt_cadastro TIMESTAMP WITHOUT TIME ZONE,
	dt_alteracao TIMESTAMP WITHOUT TIME ZONE,
	ativo BOOLEAN DEFAULT TRUE,
	email VARCHAR(50) NOT NULL,
	senha VARCHAR(50) NOT NULL,
        UNIQUE (email),
	CONSTRAINT pk_adm PRIMARY KEY (id, email)
);

CREATE TABLE IF NOT EXISTS public.tb_cliente
(
    id integer NOT NULL DEFAULT nextval('tb_cliente_id_seq'::regclass),
    dt_cadastro timestamp without time zone,
    dt_alteracao timestamp without time zone,
    email character varying(50) COLLATE pg_catalog."default" NOT NULL,
    senha character varying(20) COLLATE pg_catalog."default" NOT NULL,
    cpf character varying(14) COLLATE pg_catalog."default" NOT NULL,
    nome character varying(50) COLLATE pg_catalog."default" NOT NULL,
    dt_nascimento character varying(10) COLLATE pg_catalog."default" NOT NULL,
    sexo character(1) COLLATE pg_catalog."default" NOT NULL,
    telefone character varying(13) COLLATE pg_catalog."default" NOT NULL,
    celular character varying(14) COLLATE pg_catalog."default" NOT NULL,
    ativo boolean DEFAULT true,
    CONSTRAINT pk_cliente PRIMARY KEY (id),
    CONSTRAINT tb_cliente_cpf_key UNIQUE (cpf),
    CONSTRAINT tb_cliente_email_key UNIQUE (email)
)

CREATE TABLE tb_estado(
	id SERIAL,
	dt_cadastro TIMESTAMP WITHOUT TIME ZONE,
	dt_alteracao TIMESTAMP WITHOUT TIME ZONE,
	nome VARCHAR(40) NOT NULL,
	uf VARCHAR(2) NOT NULL,
	ativo BOOLEAN DEFAULT TRUE,

	CONSTRAINT pk_estado PRIMARY KEY (id)
);

CREATE TABLE tb_cidade(
	id SERIAL,
	dt_cadastro TIMESTAMP WITHOUT TIME ZONE,
	dt_alteracao TIMESTAMP WITHOUT TIME ZONE,
	nome VARCHAR(40) NOT NULL,
	ativo BOOLEAN DEFAULT TRUE,
	fk_estado NOT NULL,

	CONSTRAINT pk_cidade PRIMARY KEY (id),
	
	CONSTRAINT fk_estado FOREIGN KEY(fk_estado)
	REFERENCES tb_estado (id) MATCH SIMPLE
	ON UPDATE RESTRICT ON DELETE RESTRICT
);

CREATE TABLE tb_tipo_residencia(
	id SERIAL,
	dt_cadastro TIMESTAMP WITHOUT TIME ZONE,
	dt_alteracao TIMESTAMP WITHOUT TIME ZONE,
	descricao VARCHAR(32) NOT NULL,
	ativo BOOLEAN DEFAULT TRUE,

	CONSTRAINT pk_tiporesidencia PRIMARY KEY (id)
);


CREATE TABLE tb_endereco(
	id SERIAL,
	dt_cadastro TIMESTAMP WITHOUT TIME ZONE,
	dt_alteracao TIMESTAMP WITHOUT TIME ZONE,
    	descricao VARCHAR(50) NOT NULL,
	logradouro VARCHAR(50) NOT NULL,
	numero VARCHAR(6) NOT NULL,
	complemento VARCHAR(50),
	bairro VARCHAR(50) NOT NULL,
	cep VARCHAR(9) NOT NULL,
	cidade VARCHAR(40) NOT NULL,
	uf VARCHAR(2) NOT NULL,
	ativo BOOLEAN DEFAULT TRUE,
	principal BOOLEAN DEFAULT FALSE,
	cobranca BOOLEAN DEFAULT FALSE,
	fk_cliente integer NOT NULL,
		
	CONSTRAINT pk_endereco PRIMARY KEY (id),
	
	CONSTRAINT fk_cliente FOREIGN KEY(fk_cliente)
	REFERENCES tb_cliente (id) MATCH SIMPLE
	ON UPDATE CASCADE ON DELETE RESTRICT

);

/* Endereço completo:
CREATE TABLE tb_endereco(
	id SERIAL,
	dt_cadastro TIMESTAMP WITHOUT TIME ZONE,
	dt_alteracao TIMESTAMP WITHOUT TIME ZONE,
	logradouro VARCHAR(20) NOT NULL,
	nome VARCHAR(50) NOT NULL,
	numero VARCHAR(6) NOT NULL,
	bairro VARCHAR(50) NOT NULL,
	cep VARCHAR(8) NOT NULL,
	ativo BOOLEAN DEFAULT TRUE,
	principal BOOLEAN DEFAULT FALSE,
	cobranca BOOLEAN DEFAULT FALSE,
	fk_cliente NOT NULL,
	fk_cidade NOT NULL,
	fk_tipo_residencia NOT NULL,
		
	CONSTRAINT pk_endereco PRIMARY KEY (id),
	
	CONSTRAINT fk_cliente FOREIGN KEY(fk_cliente)
	REFERENCES tb_cliente (id) MATCH SIMPLE
	ON UPDATE CASCADE ON DELETE RESTRICT,
	
	CONSTRAINT fk_cidade FOREIGN KEY(fk_cidade)
	REFERENCES tb_cidade (id) MATCH SIMPLE
	ON UPDATE CASCADE ON DELETE RESTRICT,

	CONSTRAINT fk_tiporesidencia FOREIGN KEY(fk_tiporesidencia)
	REFERENCES tb_tiporesidencia (id) MATCH SIMPLE
	ON UPDATE CASCADE ON DELETE RESTRICT
);
*/

CREATE TABLE tb_cor(
	id SERIAL,
	dt_cadastro TIMESTAMP WITHOUT TIME ZONE,
	dt_alteracao TIMESTAMP WITHOUT TIME ZONE,
	ativo BOOLEAN DEFAULT TRUE,
	nome VARCHAR(30) NOT NULL,
	
	CONSTRAINT pk_cor PRIMARY KEY (id)
);

CREATE TABLE tb_armazenamento(
	id SERIAL,
	dt_cadastro TIMESTAMP WITHOUT TIME ZONE,
	dt_alteracao TIMESTAMP WITHOUT TIME ZONE,
	ativo BOOLEAN DEFAULT TRUE,
	descricao VARCHAR(20) NOT NULL,
	
	CONSTRAINT pk_armazenamento PRIMARY KEY (id)
);

CREATE TABLE tb_marca(
	id SERIAL,
	dt_cadastro TIMESTAMP WITHOUT TIME ZONE,
	dt_alteracao TIMESTAMP WITHOUT TIME ZONE,
	ativo BOOLEAN DEFAULT TRUE,
	descricao VARCHAR(20) NOT NULL,

	CONSTRAINT pk_marca PRIMARY KEY (id)
);

CREATE TABLE tb_so(
	id SERIAL,
	dt_cadastro TIMESTAMP WITHOUT TIME ZONE,
	dt_alteracao TIMESTAMP WITHOUT TIME ZONE,
	ativo BOOLEAN DEFAULT TRUE,
	descricao VARCHAR(30) NOT NULL,
	
	CONSTRAINT pk_so PRIMARY KEY (id)
);

CREATE TABLE tb_celular(
	id SERIAL NOT NULL,
	dt_cadastro TIMESTAMP WITHOUT TIME ZONE now(),
	dt_alteracao TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
	descricao VARCHAR(50) NOT NULL,
	tipo_chip VARCHAR(20) NOT NULL,
	camera_traseira VARCHAR(20) NOT NULL,
	camera_frontal VARCHAR(20) NOT NULL,
	tamanho_tela VARCHAR(20) NOT NULL, 6''
	resolucao VARCHAR(20) NOT NULL, --1280x1080
	altura VARCHAR(10) NOT NULL,
	largura VARCHAR(10) NOT NULL,
	comprimento VARCHAR(10) NOT NULL,
	peso VARCHAR(10) NOT NULL,
	ram VARCHAR(10) NOT NULL,
	processador VARCHAR(30) NOT NULL,
	componentes VARCHAR(50) NOT NULL,
	preco numeric(10,2) NOT NULL DEFAULT 0,
    foto character varying(100),
    fk_precificacao integer,
		
	--fk_cor NOT NULL,
	--fk_armazenamento NOT NULL,
	--fk_marca NOT NULL,
	--fk_so NOT NULL,
	--fk_preco_custo NOT NULL,
	--fk_estoque NOT NULL,
	
	CONSTRAINT pk_celular PRIMARY KEY (id),
	
	CONSTRAINT fk_cor FOREIGN KEY(fk_cor)
	REFERENCES tb_cor(id) MATCH SIMPLE
	ON UPDATE RESTRICT ON DELETE RESTRICT,
	
	CONSTRAINT fk_armazenamento FOREIGN KEY(fk_armazenamento)
	REFERENCES tb_armazenamento(id) MATCH SIMPLE
	ON UPDATE RESTRICT ON DELETE RESTRICT,

	CONSTRAINT fk_marca FOREIGN KEY(fk_marca)
	REFERENCES tb_marca(id) MATCH SIMPLE
	ON UPDATE RESTRICT ON DELETE RESTRICT,

	CONSTRAINT fk_so FOREIGN KEY(fk_so)
	REFERENCES tb_so(id) MATCH SIMPLE
	ON UPDATE RESTRICT ON DELETE RESTRICT,

	/*CONSTRAINT fk_FOREIGN KEY(fk_preco_custo)
	REFERENCES tb_preco_custo(id) MATCH SIMPLE
	ON UPDATE RESTRICT ON DELETE RESTRICT*/
);

ALTER TABLE tb_celular ADD COLUMN fk_precificacao INTEGER;

ALTER TABLE tb_celular ADD CONSTRAINT fk_precificacao FOREIGN KEY(fk_precificacao)
	REFERENCES tb_precificacao (id) MATCH SIMPLE
	ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE tb_celular ADD COLUMN foto character varying(100);

/*CREATE TABLE tb_estoque(
	id SERIAL,
	dt_cadastro TIMESTAMP WITHOUT TIME ZONE,
	dt_alteracao TIMESTAMP WITHOUT TIME ZONE,
	qtde_estoque INTEGER NOT NULL,
	qtde_bloqueada INTEGER NOT NULL,
	fk_celular integer NOT NULL,
	
	CONSTRAINT pk_estoque PRIMARY KEY (id),
		
	CONSTRAINT fk_celular FOREIGN KEY(fk_celular)
	REFERENCES tb_celular(id) MATCH SIMPLE
	ON UPDATE CASCADE ON DELETE CASCADE
);*/

CREATE TABLE tb_estoque(
	fk_celular integer NOT NULL,
	dt_cadastro TIMESTAMP WITHOUT TIME ZONE,
	dt_alteracao TIMESTAMP WITHOUT TIME ZONE,
	qtde_estoque INTEGER NOT NULL DEFAULT 0,
	qtde_bloqueada INTEGER NOT NULL DEFAULT 0,
	qtde_min_estoque INTEGER NOT NULL DEFAULT 0,
	
	CONSTRAINT pk_estoque PRIMARY KEY (fk_celular),
		
	CONSTRAINT fk_celular FOREIGN KEY(fk_celular)
	REFERENCES tb_celular(id) MATCH SIMPLE
	ON UPDATE CASCADE ON DELETE RESTRICT
);

--ALTER TABLE tb_estoque ADD COLUMN qtde_min_estoque INTEGER NOT NULL DEFAULT 0;

CREATE TABLE tb_carrinho(
	fk_cliente integer NOT NULL,
	dt_cadastro TIMESTAMP WITHOUT TIME ZONE,
	dt_alteracao TIMESTAMP WITHOUT TIME ZONE,
	ativo BOOLEAN DEFAULT TRUE,
	preco_total NUMERIC(10,2) NOT NULL DEFAULT 0,
	
	CONSTRAINT pk_carrinho PRIMARY KEY (fk_cliente),
	
	CONSTRAINT fk_cliente FOREIGN KEY(fk_cliente)
	REFERENCES tb_cliente (id) MATCH SIMPLE
	ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE tb_carrinhoi(
	id SERIAL NOT NULL,
	dt_cadastro TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
	dt_alteracao TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
	ativo BOOLEAN DEFAULT TRUE,
	preco_uni NUMERIC(10,2) NOT NULL,
	qtde INTEGER NOT NULL,
        --preco_totalitem NUMERIC(12,2) NOT NULL,
	fk_celular INTEGER NOT NULL,
	fk_carrinho INTEGER NOT NULL,
	
	CONSTRAINT pk_carrinhoi PRIMARY KEY (id),

	CONSTRAINT fk_celular FOREIGN KEY(fk_celular)
	REFERENCES tb_celular(id) MATCH SIMPLE
	ON UPDATE CASCADE ON DELETE RESTRICT,
	
	CONSTRAINT fk_carrinho FOREIGN KEY(fk_carrinho)
	REFERENCES tb_carrinho(fk_cliente) MATCH SIMPLE
	ON UPDATE CASCADE ON DELETE CASCADE,
	
	CONSTRAINT tb_carrinhoi_produto_key UNIQUE (fk_carrinho, fk_celular)
);

--ALTER TABLE tb_carrinhoi ADD CONSTRAINT tb_carrinhoi_produto_key UNIQUE (fk_carrinho, fk_celular);

CREATE TABLE tb_status_pedido(
	id SERIAL,
	dt_cadastro TIMESTAMP WITHOUT TIME ZONE,
	dt_alteracao TIMESTAMP WITHOUT TIME ZONE,
	ativo BOOLEAN DEFAULT TRUE,
	descricao VARCHAR(30) NOT NULL,

	CONSTRAINT pk_status_pedido PRIMARY KEY (id)
);

CREATE TABLE tb_pedido(
	id SERIAL,
	dt_cadastro TIMESTAMP WITHOUT TIME ZONE,
	dt_alteracao TIMESTAMP WITHOUT TIME ZONE,
	ativo BOOLEAN NOT NULL DEFAULT TRUE,
	confirmado BOOLEAN NOT NULL DEFAULT FALSE,
	vlfrete NUMERIC (6,2) NOT NULL DEFAULT 0,
	totalitens NUMERIC (10,2) NOT NULL DEFAULT 0,
	total NUMERIC(10,2) NOT NULL DEFAULT 0,
	logradouro VARCHAR(20),
	numero VARCHAR(6),
	bairro VARCHAR(50),
	cep VARCHAR(9),
	cidade character varying(40),
	uf character varying(2),
	
	fk_cliente INTEGER NOT NULL,
	fk_endereco INTEGER,
	status_pedido INTEGER,
	
	CONSTRAINT pk_pedido PRIMARY KEY (id),

	CONSTRAINT fk_cliente FOREIGN KEY(fk_cliente)
	REFERENCES tb_cliente (id) MATCH SIMPLE
	ON UPDATE CASCADE ON DELETE RESTRICT,
	
	CONSTRAINT fk_endereco FOREIGN KEY(fk_endereco)
	REFERENCES tb_endereco (id) MATCH SIMPLE
	ON UPDATE CASCADE ON DELETE SET NULL
	
);

CREATE TABLE IF NOT EXISTS public.tb_pedidoi
(
    id SERIAL NOT NULL,
    dt_cadastro timestamp without time zone DEFAULT now(),
    dt_alteracao timestamp without time zone DEFAULT now(),
    ativo boolean DEFAULT true,
    preco_uni numeric(10,2) NOT NULL DEFAULT 0,
    qtde integer NOT NULL,
	totalitem numeric(10,2) NOT NULL DEFAULT 0,
    fk_celular integer NOT NULL,
    fk_pedido integer NOT NULL,
    CONSTRAINT pk_pedidoi PRIMARY KEY (id),
	
    CONSTRAINT tb_pedidoi_produto_key UNIQUE (fk_pedido, fk_celular),
	
    CONSTRAINT fk_pedido FOREIGN KEY (fk_pedido)
        REFERENCES public.tb_pedido (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_celular FOREIGN KEY (fk_celular)
        REFERENCES public.tb_celular (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ;

CREATE TABLE tb_bandeira(
	id SERIAL,
	dt_cadastro TIMESTAMP WITHOUT TIME ZONE,
	dt_alteracao TIMESTAMP WITHOUT TIME ZONE,
	ativo BOOLEAN DEFAULT TRUE,
	descricao VARCHAR(50) NOT NULL,

	CONSTRAINT pk_bandeira PRIMARY KEY (id)
);

CREATE TABLE tb_cartao(
	id SERIAL,
	dt_cadastro TIMESTAMP WITHOUT TIME ZONE,
	dt_alteracao TIMESTAMP WITHOUT TIME ZONE,
	ativo BOOLEAN DEFAULT TRUE,
	descricao VARCHAR(50) NOT NULL,
	numero VARCHAR(20) NOT NULL,
	mes VARCHAR(2) NOT NULL,
	ano VARCHAR(4) NOT NULL,
	codigo VARCHAR(3) NOT NULL,
	nome_titular VARCHAR(50) NOT NULL,
	cpf_titular VARCHAR(11) NOT NULL,
	fk_cliente integer NOT NULL,
	fk_bandeira,

	CONSTRAINT pk_cartao PRIMARY KEY (id),

	CONSTRAINT fk_cliente FOREIGN KEY(fk_cliente)
	REFERENCES tb_cliente (id) MATCH SIMPLE
	ON UPDATE RESTRICT ON DELETE RESTRICT
);
	
CREATE TABLE tb_cupom_troca(
	id SERIAL,
	dt_cadastro TIMESTAMP WITHOUT TIME ZONE,
	dt_alteracao TIMESTAMP WITHOUT TIME ZONE,
	ativo BOOLEAN DEFAULT TRUE,
	codigo VARCHAR(20) NOT NULL,
	valor DOUBLE NOT NULL,
	fk_cliente NOT NULL,
	fk_troca NOT NULL

	CONSTRAINT pk_cupom PRIMARY KEY (id, codigo),

	CONSTRAINT fk_cliente FOREIGN KEY(fk_cliente)
	REFERENCES tb_cliente (id) MATCH SIMPLE
	ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE tb_pagamento(
	id SERIAL,
	dt_cadastro TIMESTAMP WITHOUT TIME ZONE,
	dt_alteracao TIMESTAMP WITHOUT TIME ZONE,
	ativo BOOLEAN DEFAULT TRUE,
	preco_total,
	fk_pedido,
        
	
	CONSTRAINT pk_pagamento PRIMARY KEY (id),
	
	CONSTRAINT fk_pedido FOREIGN KEY (fk_pedido)
	REFERENCES tb_pedido (id) MATCH SIMPLE
	ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE tb_pagamentoi(
	id SERIAL,
	dt_cadastro TIMESTAMP WITHOUT TIME ZONE,
	dt_alteracao TIMESTAMP WITHOUT TIME ZONE,
	ativo BOOLEAN DEFAULT TRUE,
	fk_pagamento NOT NULL,
	tipo INTEGER NOT NULL,
	forma_pagamento INTEGER NOT NULL, 	--chaves estrangeiras de cartão ou cupom
	parcelas INTEGER NOT NULL,

	CONSTRAINT pk_dados_pagamento PRIMARY KEY (id),

	CONSTRAINT fk_pagamento FOREIGN KEY(fk_pagamento)
	REFERENCES tb_pagamento (id) MATCH SIMPLE
	ON UPDATE RESTRICT ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS tb_precificacao (
      id serial NOT NULL
	, descricao character varying(30)
	, pct numeric(5,2)
	, CONSTRAINT pk_precificacao PRIMARY KEY(id)
);

CREATE OR REPLACE FUNCTION trg_cliente_carrinho()
  RETURNS TRIGGER 
  LANGUAGE PLPGSQL
  AS
$$
BEGIN
	INSERT INTO tb_carrinho(fk_cliente
						  , dt_cadastro
						  , dt_alteracao)
	VALUES(NEW.id
	      ,NOW()
	      ,NOW());
	RETURN NEW;
END;
$$;

CREATE TRIGGER trg_cliente_carrinho
  AFTER INSERT
  ON tb_cliente
  FOR EACH ROW
  EXECUTE PROCEDURE trg_cliente_carrinho();

CREATE OR REPLACE FUNCTION public.trg_celular_estoque()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
BEGIN
	INSERT INTO tb_estoque(fk_celular
						  , dt_cadastro
						  , dt_alteracao
                                                  , qtde_)
	VALUES(NEW.id
	      ,NOW()
	      ,NOW());
	RETURN NEW;
END;
$BODY$;

CREATE TRIGGER trg_celular_estoque
    AFTER INSERT
    ON public.tb_celular
    FOR EACH ROW
    EXECUTE FUNCTION public.trg_celular_estoque();

CREATE OR REPLACE FUNCTION public.trg_carrinhoi_carrinho_atualizapreco()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
BEGIN
	
	IF tg_op = 'INSERT' OR tg_op = 'UPDATE' THEN
	
		UPDATE tb_carrinho
		SET preco_total = preco_total + (new.qtde * new.preco_uni)
		WHERE fk_cliente = new.fk_carrinho;
	
	END IF;
	
	IF tg_op = 'UPDATE' OR tg_op = 'DELETE' THEN
		
		UPDATE tb_carrinho
		SET preco_total = preco_total - (old.qtde * old.preco_uni)
		WHERE fk_cliente = old.fk_carrinho;
		
	END IF;
	
	IF tg_op = 'DELETE' THEN
		RETURN old;
	ELSE
		RETURN new;
	END IF;
	
END;
$BODY$;

CREATE TRIGGER trg_carrinhoi_carrinho_atualizapreco
    AFTER INSERT OR UPDATE OR DELETE
    ON public.tb_carrinhoi
    FOR EACH ROW
    EXECUTE FUNCTION public.trg_carrinhoi_carrinho_atualizapreco();

-----

CREATE OR REPLACE FUNCTION public.trg_pedidoi_pedido_atualizapreco()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
BEGIN
	
	IF tg_op = 'INSERT' OR tg_op = 'UPDATE' THEN
	
		UPDATE tb_pedido
		SET totalitens = totalitens + (new.qtde * new.preco_uni)
		WHERE id = new.fk_pedido;
		
		UPDATE tb_pedido
		SET total = totalitens + vlfrete
		WHERE id = new.fk_pedido;
	
	END IF;
	
	IF tg_op = 'UPDATE' OR tg_op = 'DELETE' THEN
		
		UPDATE tb_pedido
		SET totalitens = totalitens - (old.qtde * old.preco_uni)
		WHERE id = old.fk_pedido;
		
		UPDATE tb_pedido
		SET total = totalitens + vlfrete
		WHERE id = old.fk_pedido;
		
	END IF;
	
	IF tg_op = 'DELETE' THEN
		RETURN old;
	ELSE
		RETURN new;
	END IF;
	
END;
$BODY$;

CREATE TRIGGER trg_pedidoi_pedido_atualizapreco
    AFTER INSERT OR UPDATE OR DELETE
    ON public.tb_pedidoi
    FOR EACH ROW
    EXECUTE FUNCTION public.trg_pedidoi_pedido_atualizapreco();

CREATE OR REPLACE FUNCTION public.trg_pedido_pedido_exclui_pedido_nao_confirmado()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
BEGIN
	
	IF tg_op = 'INSERT' THEN
		
		DELETE
		FROM tb_pedido
		WHERE fk_cliente = new.fk_cliente AND confirmado = false ;
		
	END IF;
	
	IF tg_op = 'DELETE' THEN
		RETURN old;
	ELSE
		RETURN new;
	END IF;
	
END;
$BODY$;

CREATE TRIGGER trg_pedido_pedido_exclui_pedido_nao_confirmado
    BEFORE INSERT
    ON public.tb_pedido
    FOR EACH ROW
    EXECUTE FUNCTION public.trg_pedido_pedido_exclui_pedido_nao_confirmado();

preço de custo e a precificação
parcelas
qual preço é qual???