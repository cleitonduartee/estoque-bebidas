# API Estoque-bebidas

#Ao baixar esse projeto e execultar em sua maquina, irá ser populados 5 secões ( Cervejas, Sucos, Refrigerantes, Vinhos e Energeticos) e também será populada alguns produtos pertencentes a essas seções.
Tanto os produtos como as Secões tem a informação das Categoria.

#Atualmente existe dois tipo de Categorias: "Alcoolica e NãoAlcoolica".

#Para acessar os dados instanciado em memória, acessar o link: http://localhost:8080/h2-console - Banco H2   

#Para acessar a documentação dessa Api no swagger, acessar o link : http://localhost:8080/swagger-ui.html

#Atualmente existem os endPoit : 

      #(GET) /historicos - retorna todos os historicos de (Cadastro, compra e venda ) de produtos.
      
      #(GET) /historicos/porCategoriaSecao - Esse end point busca todos os historico que for informado no parametro de categoria e sua respectiva seção.
      
      #(GET) /historicos/{id} - Esse end point retorna o historico conforme o ID informado.
      
      #(GET) /produtos -  retorna todos os produtos cadastrados.
      
      #(POST) /produtos/cadastrar - Esse end point faz realiza um cadastro de produto. 
      
      #(POST) /produtos/compra - Esse end point realiza uma compra do produto.
      
      #(POST) /produtos/venda - Esse end point realiza uma venda do produto
      
      #(GET) /produtos/findByCategoira - Esse end point retorna os produtos conforme a Categoria informada.
      
      #(GET) /produtos/{id} - Esse end point retorna o produto conforme o ID informado.
      
      #(GET) /secao/disponivelEntrada - esse end point verifica qual seção está disponível para entrada conforme o volume informado.
      
      #(GET) /secao/disponivelSaida - esse end point verifica qual seção tem estoque para venda conforme a categoria informada.
      
      #(GET) /secao/volumePorCategoria - esse end point retorna o total em estoque das categorias.
      
      
      
