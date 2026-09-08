Feature: Paso 1 - Ingresar informacion

  Scenario Outline: Test curp
    Given doy clic en el elemento "curp" pagina ingresar informacion
    When ingreso "<curp>" en el campo "CURP" de la pagina ingresar informacion
    And doy clic en el elemento "aviso de privacidad" pagina ingresar informacion
    Examples:
      | curp               |
      | ASPK000100HDFHVG01 |
      | PPOO990000HDFHVG02 |