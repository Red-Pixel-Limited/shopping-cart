package shopping.basket

import shopping.product.Product
import shopping.basket.ShoppingCart.Quantity

import scala.math.BigDecimal.RoundingMode

final case class ShoppingCart private(items: Map[Product, Quantity]):

  def get(product: Product): Quantity =
    items.getOrElse(product, 0)

  def add(product: Product, count: Quantity): ShoppingCart =
    copy(items.updated(product, items.get(product).foldLeft(count)(_ + _)))

  def total: BigDecimal =
    items
      .foldLeft(BigDecimal(0))((amount, item) => amount + (item._1.price * item._2))
      .setScale(2, RoundingMode.HALF_UP)

object ShoppingCart:

  type Quantity = Int
  type CartItem = (Product, Quantity)

  def empty: ShoppingCart = ShoppingCart(Map.empty)

