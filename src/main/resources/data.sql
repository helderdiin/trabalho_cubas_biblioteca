insert into Usuario (id, username, email, password) values
	(1, 'helder', 'helder@helder.com', '$2a$10$NAs20m/6lzXqIo33udgxkuJJ2E4W3QhIEKIlDdpl0W9OMhuTOIqz2'),
	(2, 'kassia', 'kassia@kassia.com', '$2a$10$NAs20m/6lzXqIo33udgxkuJJ2E4W3QhIEKIlDdpl0W9OMhuTOIqz2'),
	(3, 'testeweeeeee', 'teste@testeeee.commmm', '$2a$10$NAs20m/6lzXqIo33udgxkuJJ2E4W3QhIEKIlDdpl0W9OMhuTOIqz2');
	
insert into Autor (id, nome) values
	(1, 'helder'),
	(2, 'marcio'),
	(3, 'gabriel'),
	(4, 'pongo');
	
insert into Cliente (id_cliente, nome, endereco, data_nascimento, observacao) values
	(1, 'kassia', 'endereco teste', '2012-02-17', 'testeee'),
	(2, 'miah', 'endereco teste da miah', '2012-02-17', 'testeee2222'),
	(3, 'julio', 'endereco do julio', '2010-03-20', 'Julioooo olha beeeeem');
	
insert into Livro (id, foto, isbn, nome, quantidade, quantidade_paginas, autor_id) values
	(1, null, '1234', 'Livro teste', 10, 300, 1),
	(2, null, '4568', 'Livro novo', 20, 280, 3),
	(3, null, '8964', 'Novo livro', 35, 310, 2);
	
insert into Emprestimo (id, data_devolucao, data_emprestimo, cliente_id_cliente, livro_id) values
	(1, '2012-02-17', '2012-02-10', 1, 1),
	(2, '2012-02-16', '2012-02-13', 1, 2),
	(3, '2012-02-01', '2012-01-05', 2, 1),
	(4, '2012-03-10', '2012-01-07', 2, 2);
	
insert into Review (id, avaliacao, comentario, livro_id, usuario_id) values
	(1, 5, 'Comentário', 1, 1),
	(2, 4, 'Avaliação', 2, 2),
	(3, 2, 'Teste de av', 1, 2),
	(4, 1, 'Teste ruim de nota', 2, 1);