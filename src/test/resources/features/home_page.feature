Feature: Navegacion en pagina One Click

  @test
  Scenario: Acceder a la solicitud desde la pagina de inicio
    Given ingresar a la pagina One Click
    Then debo visualizar los elementos de la pagina "inicio"
    And pausa 10 segundos
    And dar click en elemento "solicitalo aqui" pagina inicio
    And pausa 10 segundos




