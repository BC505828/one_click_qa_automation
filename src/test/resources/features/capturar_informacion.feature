Feature: Paso 1 - Ingresar informacion

  Scenario Outline: Test curp
    Given dar click en elemento "solicitalo aqui" pagina inicio
    When ingreso "<curp>" en el campo "CURP" de la pagina ingresar informacion
    And doy clic en el elemento "aviso de privacidad" pagina ingresar informacion
    And doy clic en el elemento "continuar" pagina ingresar informacion
    Examples:
      | curp               |
      | ASPK000100HDFHVG01 |