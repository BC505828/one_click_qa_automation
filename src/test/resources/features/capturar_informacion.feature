Feature: Paso 1 - Ingresar informacion

  @ingresacurp
  Scenario Outline: Test curp
    Given ingresar a la pagina One Click
    When dar click en elemento "solicitalo aqui" pagina inicio
    When ingreso <curp> en el campo "CURP" de la pagina ingresar informacion
    And ocultar teclado
    And doy clic en el elemento "area" pagina ingresar informacion
    And doy clic en el elemento "privacidad" pagina ingresar informacion
    Then debo visualizar los elementos <error> de la pagina captura de informacion paso 1
    Examples:
      | curp                 | error         |
      | "234567890946534553" | curp_invalida |
      #| 234567890946534553 |               |