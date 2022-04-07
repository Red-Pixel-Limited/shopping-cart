package shopping.product

sealed abstract class Product(val name: String, val price: BigDecimal)

object Product:

  def apply(name: String, price: BigDecimal): Either[ProductError, Product] =
    for {
      productName  <- Either.cond(!name.isBlank, name, ProductError("Product name is required"))
      productPrice <- Either.cond(price > 0, price, ProductError("Price must be greater than 0"))
    } yield new Product(productName, productPrice) {}

  final case class DoveSoap(override val price: BigDecimal)
    extends Product(name = "Dove Soap", price)

  final case class AxeDeo(override val price: BigDecimal)
    extends Product(name = "Axe Deo", price)
