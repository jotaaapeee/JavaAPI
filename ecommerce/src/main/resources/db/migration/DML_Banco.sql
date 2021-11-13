INSERT  INTO endereco(cep, rua, bairro, cidade, numero, complemento, estado)
values(01001-000, 'Praça da Sé', 'Sé', 'São Paulo', 256, 'lado ímpar', 'SP');

INSERT  INTO endereco(cep, rua, bairro, cidade, numero, complemento, estado)
values(24210-230,'Rua José Bonifácio', 'São Domingos', 'Niterói', 46, 'Apto 302', 'RJ');

INSERT  INTO endereco(cep, rua, bairro, cidade, numero, complemento, estado)
values(25680-400, 'Rua Doutor Hans Bistrischan', 'Retiro', 'Petrópolis', 185, 'Casa verde', 'RJ');

INSERT  INTO endereco(cep, rua, bairro, cidade, numero, complemento, estado)
values(66617-160, 'Travessa Itaituba', 'Val-de-Cães', 'Belém', 189, 'Bloco B Apto 2201', 'PA');

INSERT  INTO endereco(cep, rua, bairro, cidade, numero, complemento, estado)
values(75712-700, 'Rua do Bougainville', 'Vila Wilson Guimarães', 'Catalão', 06, 'Casa dos fundos', 'GO');

INSERT  INTO endereco(cep, rua, bairro, cidade, numero, complemento, estado)
values(35305-011, 'Rua José Alexandrino Campos', 'Graça', 'Caratinga', 774, 'Com torre telefonica', 'MG');


INSERT  INTO categoria(nome,descricao)
values('Hardware', 'Peças de computador individuais');

INSERT  INTO categoria(nome, descricao)
values('Consoles', 'Video game para voce curtir seus jogos favoritos');

INSERT  INTO categoria(nome, descricao)
values('Pseudo-celulares', 'Imitações baratas ou tijolos dentro de uma caixa :)');

INSERT  INTO categoria(nome, descricao)
values('TV', 'Para assistir seus filmes e séries favoritos');

INSERT  INTO categoria(nome, descricao)
values('Audio', 'Dispositivos de gravação, reprodução de audio');

INSERT INTO categoria(nome, descricao) 
values('Software','Licenças não licensiadas');


INSERT  INTO produto (nome, descricao, qtd_estoque, data_cadastro, valor_unitario, id_categoria)
values('GTX 1050TI', 'Placa de vídeo 4GB ram', 30, '2016-09-21', 999.99, 1);

INSERT  INTO produto (nome, descricao, qtd_estoque, data_cadastro, valor_unitario, id_categoria)
values('PS5', 'Playstation 5 1TB SSD s/ suporte para mídia fisica', 40, '2020-06-02', 6500.00, 2);

INSERT  INTO produto (nome, descricao, qtd_estoque, data_cadastro, valor_unitario, id_categoria)
values('Chyômi', 'Celular com 37 câmeras e 1GB ram', 8, '2077-05-04', 40.20, 3);

INSERT  INTO produto (nome, descricao, qtd_estoque, data_cadastro, valor_unitario, id_categoria)
values('TV SMart 4K', 'Sony TV Smart 4K com bordas invisiveis', 300, '2020-02-29', 5600.00, 4);

INSERT  INTO produto (nome, descricao, qtd_estoque, data_cadastro, valor_unitario, id_categoria)
values('Hyper Cloud II', 'USB 7.1 Surround', 3, '2019-06-25', 650.00, 5);

INSERT INTO produto (nome, descricao, qtd_estoque, data_cadastro, valor_unitario, id_categoria)
values ('Adobe Photoshop', 'Licença', 20, '2020-08-11', 99.90, 6);




INSERT INTO cliente (email, nome_usuario, nome_completo, senha, cpf, telefone, data_nasc, id_endereco) 
values ('jpedro@gmail.com', 'jotape', 'Joao Pedro', 'joao123', '21864058226', '24981985631', '2000-07-17', 1);

INSERT INTO cliente (email, nome_usuario, nome_completo, senha, cpf, telefone, data_nasc, id_endereco) 
values('cjusten@gmail.com', 'cjusten', 'Carolina', 'carol123', '84362043446', '21985631447', '1994-09-10', 2);

INSERT INTO cliente (email, nome_usuario, nome_completo, senha, cpf, telefone, data_nasc, id_endereco) 
values('macedoluan@gmail.com', 'Lmacedo', 'Luan', 'luan123', '89101418750', '24985633445', '2000-09-10', 3);

INSERT INTO cliente (email, nome_usuario, nome_completo, senha, cpf, telefone, data_nasc, id_endereco) 
values('Nyck@gmail.com', 'nmilitao', 'Nyckole', 'nyck123', '66273209405', '24985631445', '1994-12-09', 4);

INSERT INTO cliente (email, nome_usuario, nome_completo, senha, cpf, telefone, data_nasc, id_endereco) 
values('roberto@gmail.com', 'RLB', 'Roberto', 'roberto123', '61062043446', '2152634785', '2000-´06-26', 5);

INSERT INTO cliente (email, nome_usuario, nome_completo, senha, cpf, telefone, data_nasc, id_endereco) 
values('sabrina@gmail.com', 'sabrina', 'Sabrina', 'sabrina123', '39419037053', '22974589125', '1990-12-01', 6);


INSERT INTO pedido (data_pedido, data_entrega, data_envio, id_cliente)
values('2021-04-03', '2021-05-03', '2021-04-14', 1); 

INSERT INTO pedido (data_pedido, data_entrega, data_envio, id_cliente)
values ('2021-01-15', '2021-02-01', '2021-01-21', 2);

INSERT INTO pedido (data_pedido, data_entrega, data_envio, id_cliente)
values ('2021-03-13', '2021-03-18', '2021-03-15', 3);

INSERT INTO pedido (data_pedido, data_entrega, data_envio, id_cliente)
values ('2021-02-14', '2021-04-01', '2021-03-15', 4);

INSERT INTO pedido (data_pedido, data_entrega, data_envio, id_cliente)
values ('2021-05-06', '2021-06-06', '2021-05-09', 5);

INSERT INTO pedido (data_pedido, data_entrega, data_envio, id_cliente)
values ('2021-04-08', '2021-04-30', '2021-04-10', 6);


INSERT  INTO item_pedido (quantidade, preco_venda, id_pedido, id_produto)
values(4 , 160.00, 1, 3);

INSERT  INTO item_pedido (quantidade, preco_venda, id_pedido, id_produto)
values(5, 7500, 2, 4);

INSERT  INTO item_pedido (quantidade, preco_venda, id_pedido, id_produto)
values(14, 2100.00, 4, 1);

INSERT  INTO item_pedido (quantidade, preco_venda, id_pedido, id_produto)
values(1, 650.00, 4, 2);

INSERT  INTO item_pedido (quantidade, preco_venda, id_pedido, id_produto)
values(3, 120.00, 5, 3);

INSERT INTO item_pedido  (quantidade, preco_venda, id_pedido, id_produto)
values(1, 70.00, 3, 6);

INSERT INTO item_pedido  (quantidade, preco_venda, id_pedido, id_produto)
values(1, 40.00, 6, 3);

INSERT INTO item_pedido  (quantidade, preco_venda, id_pedido, id_produto)
values (14, 980.00, 6, 6);