Feature: Paso 1 - Ingresar informacion

  @ingresacurp
  Scenario Outline: Test curp
    Given ingresar a la pagina One Click
    When dar click en elemento "solicitalo aqui" pagina inicio
    When ingreso "<curp>" en el campo "CURP" de la pagina ingresar informacion
    And ocultar teclado
    And pausa 5 segundos
    And doy clic en el elemento "privacidad" pagina ingresar informacion
    And doy clic en el elemento "continuar" pagina ingresar informacion
    And pausa 10 segundos
    Examples:
      | curp               |
      | ASPK000100HDFHVG01 |
