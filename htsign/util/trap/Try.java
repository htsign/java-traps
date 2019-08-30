package htsign.util.trap;

import htsign.util.function.Consumer;
import htsign.util.function.Function;
import htsign.util.function.ThrowableFunction;
import htsign.util.function.ThrowableSupplier;

public abstract class Try<T, E extends Throwable>
{
	public abstract boolean isSuccess();
	public abstract boolean isFailure();
	public abstract <R> Try<R, E> map(ThrowableFunction<? super T, ? extends R> function);
	public abstract <R> Try<R, E> flatMap(ThrowableFunction<? super T, ? extends Try<R, E>> function);
	public abstract void consume(Consumer<? super T> consumer);
	public abstract void match(Consumer<? super E> onFailure, Consumer<? super T> onSuccess);
	public abstract <U> U match(Function<? super E, ? extends U> onFailure, Function<? super T, ? extends U> onSuccess);
	public abstract Either<E, T> toEither();

	@SuppressWarnings("unchecked")
	public static <R, E extends Throwable> Try<R, E> of(ThrowableSupplier<R> supplier)
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
	public static <T, R, E extends Throwable> Try<R, E> of(ThrowableFunction<? super T, ? extends R> function, T arg)
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
