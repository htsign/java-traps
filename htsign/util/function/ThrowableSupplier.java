package htsign.util.function;

public interface ThrowableSupplier<T>
{
	<E extends Throwable> T getThrows() throws E;
}
