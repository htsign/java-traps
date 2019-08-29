package htsign.util.trap;

import htsign.util.function.ThrowableFunction;
import htsign.util.function.ThrowableSupplier;

public abstract class Try<T, E extends Throwable>
{
	public abstract boolean isSuccess();
	public abstract boolean isFailure();
	public abstract <R> Try<R, E> map(ThrowableFunction<T, R> function);
	public abstract <R> Try<R, E> flatMap(ThrowableFunction<T, Try<R, E>> function);
	public abstract Either<E, T> toEither();

	@SuppressWarnings("unchecked")
	public static <R, E extends Throwable> Try<R, E> of(ThrowableSupplier<R, E> supplier)
	{
		try
		{
			return new Success<R, E>(supplier.getThrows());
		}
		catch (Throwable throwable)
		{
			return new Failure<R, E>((E) throwable);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T, R, E extends Throwable> Try<R, E> of(ThrowableFunction<T, R> function, T arg)
	{
		try
		{
			return new Success<R, E>(function.applyThrows(arg));
		}
		catch (Throwable throwable)
		{
			return new Failure<R, E>((E) throwable);
		}
	}
}
