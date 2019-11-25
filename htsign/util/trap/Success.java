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
public class Success<T, E extends Throwable> extends Try<T, E>
{
	private T value;

	/** {@inheritDoc} */
	@Override
	public boolean isSuccess()
	{
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isFailure()
	{
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public <R> Try<R, E> map(ThrowableFunction<? super T, ? extends R, E> function)
	{
		return Try.of(function, getValue());
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public <R> Try<R, E> flatMap(ThrowableFunction<? super T, ? extends Try<R, E>, E> function)
	{
		try
		{
			return function.applyThrows(getValue());
		}
		catch (Throwable throwable)
		{
			return new Failure<R, E>((E) throwable);
		}
	}

	/** {@inheritDoc} */
	@Override
	public void consume(Consumer<? super T> consumer)
	{
		consumer.accept(getValue());
	}

	/** {@inheritDoc} */
	@Override
	public void match(Consumer<? super E> onFailure, Consumer<? super T> onSuccess)
	{
		onSuccess.accept(getValue());
	}

	/** {@inheritDoc} */
	@Override
	public <U> U match(Function<? super E, ? extends U> onFailure, Function<? super T, ? extends U> onSuccess)
	{
		return onSuccess.apply(getValue());
	}

	/** {@inheritDoc} */
	@Override
	public Either<E, T> toEither()
	{
		return Right.of(getValue());
	}
}
