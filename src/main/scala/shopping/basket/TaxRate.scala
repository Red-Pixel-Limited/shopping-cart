package shopping.basket

import scala.math.BigDecimal.RoundingMode

opaque type TaxRate = BigDecimal

object TaxRate:
  extension (taxRate: TaxRate)
    def on(shoppingCart: ShoppingCart): BigDecimal =
      (taxRate * shoppingCart.total).setScale(1, RoundingMode.HALF_UP)

  extension (n: Double)
    def percentage: TaxRate = n / 100
