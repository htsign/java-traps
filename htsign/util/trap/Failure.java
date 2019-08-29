package htsign.util.trap;

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
	public <R> Try<R, E> map(ThrowableFunction<T, R> function)
	{
		return (Try<R, E>) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <R> Try<R, E> flatMap(ThrowableFunction<T, Try<R, E>> function)
	{
		return (Try<R, E>) this;
	}

	@Override
	public Either<E, T> toEither()
	{
		return Left.of(getThrowable());
	}
}
