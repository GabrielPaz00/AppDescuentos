

# Plan de Pruebas de Integración: Módulo de Descuentos
**Componente:** `DescuentosViewModel` 

**Enfoque:** Incremental (Bottom-Up)

---

### TC-INT-01: Cálculo exitoso de descuento válido (Flujo Nominal)
| Campo | Detalle |
| :--- | :--- |
| **Identificador** | TC-INT-01 |
| **Objetivo** | Verificar que un descuento dentro del rango (0-100) procesa el estado correctamente. |
| **Precondiciones** | ViewModel instanciado; `DescuentosState` en valores por defecto. |
| **Datos de Entrada** | `precio`: "1000.0", `descuento`: "15.0" |
| **Pasos de Ejecución** | 1. Invocar `onPriceChange("1000.0")`<br>2. Invocar `onDiscountChange("15.0")`<br>3. Ejecutar función `calculate()` |
| **Resultado Esperado** | `validDiscount`: true<br>`discount`: 150.0 (Format: "150.00")<br>`total`: 850.0 (Format: "850.00") |

---

### TC-INT-02: Bloqueo de cálculo por límite superior excedido
| Campo | Detalle |
| :--- | :--- |
| **Identificador** | TC-INT-02 |
| **Objetivo** | Validar la regla de negocio que impide procesar descuentos mayores al 100%. |
| **Precondiciones** | ViewModel en estado inicial. |
| **Datos de Entrada** | `precio`: "500.0", `descuento`: "101.0" |
| **Pasos de Ejecución** | 1. Invocar `onPriceChange("500.0")`<br>2. Invocar `onDiscountChange("101.0")`<br>3. Ejecutar función `calculate()` |
| **Resultado Esperado** | `validDiscount`: **false**<br>La operación se aborta: `discount` y `total` permanecen en **0.0**. |

---


### TC-INT-03: Restauración integral del estado (Limpiar)
| Campo | Detalle                                                                                                            |
| :--- |:-------------------------------------------------------------------------------------------------------------------|
| **Identificador** | TC-INT-05                                                                                                          |
| **Objetivo** | Validar que la función de limpieza purga de memoria los datos operacionales.                                       |
| **Precondiciones** | ViewModel con estado (`priceInput`="250.0", `total`="225.0").                                                      |
| **Datos de Entrada** | N/A                                                                                                                |
| **Pasos de Ejecución** | 1. Ejecutar la función `clear()`                                                                                   |
| **Resultado Esperado** | Inputs (`priceInput`/`discountInput`) vacíos `""`<br>`discount`/`total` en **0.0**<br>`validDiscount` en **null**. |

---

### TC-INT-04: Análisis de Valor Límite (Descuento del 100%)
| Campo | Detalle |
| :--- | :--- |
| **Identificador** | TC-INT-03 |
| **Objetivo** | Comprobar el comportamiento matemático exacto en la frontera máxima permitida. |
| **Precondiciones** | ViewModel en estado inicial. |
| **Datos de Entrada** | `precio`: "200.0", `descuento`: "100.0" |
| **Pasos de Ejecución** | 1. Invocar `onPriceChange("200.0")`<br>2. Invocar `onDiscountChange("100.0")`<br>3. Ejecutar función `calculate()` |
| **Resultado Esperado** | `validDiscount`: true<br>`discount`: 200.0<br>`total`: 0.0 |

---

### TC-INT-05: Análisis de Valor Límite (Descuento del 0%)
| Campo | Detalle |
| :--- | :--- |
| **Identificador** | TC-INT-04 |
| **Objetivo** | Comprobar el comportamiento matemático exacto en la frontera mínima permitida. |
| **Precondiciones** | ViewModel en estado inicial. |
| **Datos de Entrada** | `precio`: "350.0", `descuento`: "0.0" |
| **Pasos de Ejecución** | 1. Invocar `onPriceChange("350.0")`<br>2. Invocar `onDiscountChange("0.0")`<br>3. Ejecutar función `calculate()` |
| **Resultado Esperado** | `validDiscount`: true<br>`discount`: 0.0<br>`total`: 350.0 |

---


