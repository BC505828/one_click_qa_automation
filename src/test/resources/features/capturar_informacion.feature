Feature: Paso 1 - Ingresar informacion


  Scenario Outline: Validar CURP <curp> invalida en captura de informacion paso 1
    Given ingresar a la pagina One Click
    When dar click en elemento "solicitalo aqui" pagina inicio
    When ingreso <curp> en el campo "CURP" de la pagina ingresar informacion
    And ocultar teclado
    And doy clic en el elemento "area" pagina ingresar informacion
    And doy clic en el elemento "privacidad" pagina ingresar informacion
    Then debo visualizar los elementos <error> de la pagina captura de informacion paso 1
    Examples:
      | curp                 | error              |
      | "234567890946534553" | curp_invalida      |
      | "CAS990000HDFHVG012" | curp_invalida      |
      | "9ASB980113XDFHVG09" | curp_invalida      |
      | "CASB990134HDFHVG09" | curp no localizada |

  @ingresacurp
  Scenario: test
    Given ingresar a la pagina One Click
    When dar click en elemento "solicitalo aqui" pagina inicio
    When ingreso "CAGC470430MTSHMT12" en el campo "CURP" de la pagina ingresar informacion
    And ocultar teclado
    And doy clic en el elemento "area" pagina ingresar informacion
    And doy clic en el elemento "privacidad" pagina ingresar informacion
    Then doy clic en el elemento "continuar" pagina ingresar informacion
    And pausa 20 segundos
    Then debo visualizar los elementos "terminos y condiciones" de la pagina captura de informacion paso 1
