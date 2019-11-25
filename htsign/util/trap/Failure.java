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

	/** {@inheritDoc} */
	@Override
	public boolean isSuccess()
	{
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isFailure()
	{
		return true;
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public <R> Try<R, E> map(ThrowableFunction<? super T, ? extends R, E> function)
	{
		return (Try<R, E>) this;
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public <R> Try<R, E> flatMap(ThrowableFunction<? super T, ? extends Try<R, E>, E> function)
	{
		return (Try<R, E>) this;
	}

	/** {@inheritDoc} */
	@Override
	public void consume(Consumer<? super T> consumer) { }

	/** {@inheritDoc} */
	@Override
	public void match(Consumer<? super E> onFailure, Consumer<? super T> onSuccess)
	{
		onFailure.accept(getThrowable());
	}

	/** {@inheritDoc} */
	@Override
	public <U> U match(Function<? super E, ? extends U> onFailure, Function<? super T, ? extends U> onSuccess)
	{
		return onFailure.apply(getThrowable());
	}

	/** {@inheritDoc} */
	@Override
	public Either<E, T> toEither()
	{
		return Left.of(getThrowable());
	}
}
