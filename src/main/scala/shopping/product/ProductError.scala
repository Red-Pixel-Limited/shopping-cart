package shopping.product

final case class ProductError(message: String)

object ProductError:
  given Conversion[ProductError, String] with
    def apply(error: ProductError): String = error.message
