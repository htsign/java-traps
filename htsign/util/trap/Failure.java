package htsign.util.trap;

import htsign.util.function.Consumer;
import htsign.util.function.Function;
import htsign.util.function.ThrowableFunction;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Value @EqualsAndHashCode(callSuper = true)
public class Failure<T, E extends Throwable> extends Try<T, E>
{
	private E throwable;

	@Override
	public boolean isSuccess()
	{
		return false;
	}

	@Override
	public boolean isFailure()
	{
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <R> Try<R, E> map(ThrowableFunction<? super T, ? extends R> function)
	{
		return (Try<R, E>) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <R> Try<R, E> flatMap(ThrowableFunction<? super T, ? extends Try<R, E>> function)
	{
		return (Try<R, E>) this;
	}

	@Override
	public void consume(Consumer<? super T> consumer) { }

	@Override
	public void match(Consumer<? super E> onFailure, Consumer<? super T> onSuccess)
	{
		onFailure.accept(getThrowable());
	}

	@Override
	public <U> U match(Function<? super E, ? extends U> onFailure, Function<? super T, ? extends U> onSuccess)
	{
		return onFailure.apply(getThrowable());
	}

	@Override
	public Either<E, T> toEither()
	{
		return Left.of(getThrowable());
	}
}
