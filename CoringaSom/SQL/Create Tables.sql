create database coringadb
default charset utf8
default collate utf8_general_ci;

use coringadb

create table usuarios(
id int primary key auto_increment,
nm_usuario varchar(200), 
id_usuario varchar(200) unique,
ds_matricula varchar(10) unique,
ds_senha varchar(200),
ds_status enum('A','I'),
in_login enum('S','N')
) default char set utf8;

insert into usuarios values (default,'Administrador','admin',0000,'557d04c9891324c194dc2845027e710a','A','N');

create table fornecedores(

id int primary key auto_increment,
nm_fornecedor varchar(200),
nr_cnpj varchar(20) unique,
nr_telefone varchar(20),
id_usuario int,
ds_status enum('A','I'),
id_email varchar(200) not null,
constraint fk_cad_forn_user foreign key(id_usuario) references usuarios(id)
) default char set utf8;

 create table tipo_produto(
 id int  auto_increment primary key,
 nm_tipo varchar(100) unique,
 ds_status enum('A','I')
 ) default char set utf8;
 
create table produtos(
id int primary key not null auto_increment,
cd_barras varchar(200) unique not null,
nm_produto varchar(200) not null,
ds_quantidade int not null,
vl_compra float not null,
vl_venda float not null, 
id_fornecedor int not null,
id_usuario int not null, 
ds_status enum('A','I'),
id_tipo int,
constraint fk_usuario foreign key (id_usuario) references usuarios(id),
constraint fk_fornecedor foreign key(id_fornecedor) references fornecedores(id),
constraint fk_tipo foreign key(id_tipo) references tipo_produto(id)
) default char set utf8;

drop table produtos;

create table historico_preco(
id int primary key not null auto_increment,
id_produto int, 
dt_alteracao date,
vl_compra float,
vl_venda float,
id_usuario int,
constraint fk_usuario_produto foreign key(id_usuario) references usuarios(id),
constraint fk_produto foreign key(id_produto) references produtos(id)) 
default char set utf8;

drop table historico_preco;

create table historico_estoque
(
id int  primary key auto_increment,
id_produto int ,
ds_movimentacao enum('C','E','S','T','V','B'),
id_usuario_movimentacao int,
id_usuario_responsavel int, 
dt_movimentacao datetime,
estoque_inicial int,
estoque_final int,
ds_observacao varchar(200),
constraint fk_user_movimenta foreign key(id_usuario_movimentacao) references
usuarios(id),
constraint fk_produto_movimenta foreign key(id_produto) references
produtos(id),
constraint fk_id_usuario_responsavel foreign key(id_usuario_responsavel) references
usuarios(id)
) default char set utf8;

drop table historico_estoque;

create table estoque(
id int primary key auto_increment,
estoque int,
id_produto int,
id_usuario int,
estoque_abaixo  enum ('S','N'),
constraint fk_id_produto_estoque foreign key(id_produto) references
produtos(id),
constraint fk_id_usuario_estoque foreign key(id_usuario) references
usuarios(id)) default char set utf8;  

drop table estoque;

create table vendas(
id int primary key auto_increment,
nr_venda int,
id_produto int,
ds_quantidade int,
id_usuario_venda int,
id_usuario_aprovador int,
ds_status_venda enum('A','P','D'),
dt_venda date,
total float,
constraint fk_user_venda foreign key(id_usuario_venda) references usuarios(id),
constraint fk_user_aprovador_venda foreign key(id_usuario_aprovador) references usuarios(id),
constraint fk_produto_venda foreign key(id_produto) references produtos(id)
) char set utf8;
alter table vendas modify ds_status_venda enum('A','P','D','C');

create table acesso(
id int primary key auto_increment,
id_usuario int,
cad_forn enum('T','F'), 
cad_func enum('T','F'), 
cad_prod enum('T','F'), 
cad_tp_prod enum('T','F'),
est_ent enum('T','F'),
est_sa enum('T','F'),
cont_acesso enum('T','F'),
solc_venda enum('T','F'),
vd_direta enum('T','F'),
apv_venda enum('T','F'),
rel_venda enum('T','F'),
rel_estq enum('T','F'),
rel_hist_estq enum('T','F')
) default charset utf8; 

/*Acess Edit> preferences > SQL Editor> SAFE UPDATES (DISABLE) */
insert into acesso (id,id_usuario,cad_forn,cad_func,cad_prod,cad_tp_prod,
est_ent,est_sa,cont_acesso,solc_venda,vd_direta,apv_venda,rel_venda,
rel_estq,rel_hist_estq)  values (default, 1,'T','T','T','T','T','T','T','T','T','T','T','T','T');




select * from vendas;

SELECT PRD.CD_BARRAS, PRD.NM_PRODUTO, PRD.ID, PRD.DS_QUANTIDADE,
        PRD.VL_COMPRA, PRD.VL_VENDA, FORN.NM_FORNECEDOR, TP.NM_TIPO FROM PRODUTOS AS PRD
        JOIN FORNECEDORES AS FORN ON FORN.ID = PRD.ID_FORNECEDOR JOIN TIPO_PRODUTO AS TP
        ON TP.ID = PRD.ID_TIPO JOIN ESTOQUE AS ESTQ ON ESTQ.ID_PRODUTO = PRD.ID WHERE ESTQ.ESTOQUE >0

SELECT PRD.CD_BARRAS, PRD.NM_PRODUTO, PRD.ID, PRD.DS_QUANTIDADE,
PRD.VL_COMPRA, PRD.VL_VENDA, FORN.NM_FORNECEDOR, TP.NM_TIPO FROM PRODUTOS AS PRD
JOIN FORNECEDORES AS FORN ON FORN.ID = PRD.ID_FORNECEDOR JOIN TIPO_PRODUTO AS TP
ON TP.ID = PRD.ID_TIPO JOIN ESTOQUE AS ESTQ ON ESTQ.ID_PRODUTO = PRD.ID WHERE ESTQ.ESTOQUE >0
select * from produtos;

SELECT PRD.NM_PRODUTO, DS_MOVIMENTACAO, USR.NM_USUARIO AS NM_RESPONSAVEL, US.NM_USUARIO AS NM_USUARIO_MOVIMENTACAO,
 DATE_FORMAT(HE.DT_MOVIMENTACAO,'%d/%m/%Y %hh:%mm:%ss') AS DT_MOVIMENTACAO, HE.ESTOQUE_INICIAL, HE.ESTOQUE_FINAL, HE.DS_OBSERVACAO
 FROM HISTORICO_ESTOQUE AS HE JOIN PRODUTOS AS PRD ON PRD.ID = HE.ID_PRODUTO JOIN USUARIOS AS US ON US.ID = 
HE.ID_USUARIO_MOVIMENTACAO JOIN USUARIOS AS USR ON USR.ID = HE.ID_USUARIO_RESPONSAVEL WHERE (null IS NULL OR PRD.CD_BARRAS=null)
AND (null IS NULL OR HE.DS_MOVIMENTACAO=null) AND (null IS NULL OR US.ID=null) AND (null IS NULL OR USR.ID=null) AND(null IS NULL  OR HE.DT_MOVIMENTACAO >= null) AND (null IS NULL  OR HE.DT_MOVIMENTACAO <= null)AND  
(PRD.NM_PRODUTO LIKE '%rad%' OR PRD.CD_BARRAS=null);

Select * from produtos;

SELECT NM_PRODUTO, ESTQ.ESTOQUE FROM ESTOQUE AS 
ESTQ JOIN PRODUTOS AS PRD ON PRD.ID = ESTQ.ID_PRODUTO WHERE 
ESTOQUE_ABAIXO='S' AND PRD.DS_STATUS!='I'

0	32	21:01:46	SELECT NM_PRODUTO, ESTQ.ESTOQUE FROM ESTOQUE AS 
 ESTQ JOIN PRODUTOS AS PRD ON PRD.ID = ESTQ.ID_PRODUTO WHERE 
 ESTOQUE_ABAIXO='S' AND PRD.DS_STATUS=!='I'	Error Code: 1064. You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right 
 syntax to use near '!='I'' at line 3	0.000 sec

SELECT PRD.NM_PRODUTO, ESTQ.ESTOQUE, ESTQ.ESTOQUE_ABAIXO FROM ESTOQUE AS ESTQ JOIN PRODUTOS AS PRD ON PRD.ID = ESTQ.ID_PRODUTO
WHERE (null IS NULL OR PRD.CD_BARRAS=null) AND (null IS NULL OR ESTQ.ESTOQUE_ABAIXO=null) AND (null IS NULL OR ESTQ.ESTOQUE >=null) 
AND (null IS NULL OR ESTQ.ESTOQUE <=null) AND (null is null or PRD.NM_PRODUTO LIKE '%null%' OR PRD.CD_BARRAS=null) ;

select * from produtos;

SELECT EST.ESTOQUE, PRD.DS_QUANTIDADE, PRD.VL_COMPRA, PRD.VL_VENDA
FROM PRODUTOS AS PRD JOIN ESTOQUE AS EST ON EST.ID_PRODUTO = PRD.ID JOIN HISTORICO_ESTOQUE AS HS_ESTQ 
ON HS_ESTQ.ID_PRODUTO = PRD.ID JOIN FORNECEDORES AS FORN ON FORN.ID = PRD.ID_FORNECEDOR WHERE PRD.ID =4 
AND PRD.DS_STATUS='A';


SELECT PRD.CD_BARRAS, PRD.NM_PRODUTO, PRD.ID, PRD.DS_QUANTIDADE,
PRD.VL_COMPRA, PRD.VL_VENDA, FORN.NM_FORNECEDOR, TP.NM_TIPO, EST.ESTOQUE FROM PRODUTOS AS PRD
JOIN FORNECEDORES AS FORN ON FORN.ID = PRD.ID_FORNECEDOR JOIN TIPO_PRODUTO AS TP
ON TP.ID = PRD.ID_TIPO JOIN ESTOQUE AS EST ON EST.ID_PRODUTO = PRD.ID WHERE EST.ESTOQUE >0 AND
  PRD.DS_STATUS !='I' AND PRD.NM_PRODUTO LIKE '%radio ios%';
  
SELECT PRD.CD_BARRAS, PRD.NM_PRODUTO, PRD.ID, PRD.DS_QUANTIDADE,
PRD.VL_COMPRA, PRD.VL_VENDA, FORN.NM_FORNECEDOR, TP.NM_TIPO, ESTQ.ESTOQUE FROM PRODUTOS AS PRD
JOIN FORNECEDORES AS FORN ON FORN.ID = PRD.ID_FORNECEDOR JOIN TIPO_PRODUTO AS TP
ON TP.ID = PRD.ID_TIPO JOIN ESTOQUE AS ESTQ ON ESTQ.ID_PRODUTO = PRD.ID WHERE ESTQ.ESTOQUE >0 AND PRD.DS_STATUS !='I'
	
    
 SELECT PRD.CD_BARRAS, PRD.NM_PRODUTO, PRD.ID, PRD.DS_QUANTIDADE,
PRD.VL_COMPRA, PRD.VL_VENDA, FORN.NM_FORNECEDOR, TP.NM_TIPO FROM PRODUTOS AS PRD
JOIN FORNECEDORES AS FORN ON FORN.ID = PRD.ID_FORNECEDOR JOIN TIPO_PRODUTO AS TP
ON TP.ID = PRD.ID_TIPO JOIN ESTOQUE AS ESTQ ON ESTQ.ID_PRODUTO = PRD.ID WHERE 
PRD.NM_PRODUTO LIKE '%gp%' AND ESTQ.ESTOQUE >0 AND PRD.DS_STATUS !='I';

SELECT PRD.CD_BARRAS, PRD.NM_PRODUTO, PRD.ID, PRD.DS_QUANTIDADE,
PRD.VL_COMPRA, PRD.VL_VENDA, FORN.NM_FORNECEDOR, TP.NM_TIPO FROM PRODUTOS AS PRD
JOIN FORNECEDORES AS FORN ON FORN.ID = PRD.ID_FORNECEDOR JOIN TIPO_PRODUTO AS TP
ON TP.ID = PRD.ID_TIPO

select * from vendas where nr_venda=67;

update vendas set ds_status_venda='P' where nr_venda=78;

select * from vendas;

alter table vendas
add vl_desconto float;

update vendas set vl_desconto=0;

create table diretorios(
id int auto_increment primary key,
diretorio varchar(200)
);

drop table diretorios;
insert into diretorios values (default,'C://caminho//Arquivo.txt');

select * from vendas


