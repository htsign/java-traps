package htsign.util.function;

public interface ThrowableFunction<T, R>
{
	<E extends Throwable> R applyThrows(T value) throws E;
}

