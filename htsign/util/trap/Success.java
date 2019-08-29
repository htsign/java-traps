package htsign.util.trap;

import htsign.util.function.ThrowableFunction;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.val;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Value @EqualsAndHashCode(callSuper = true)
public class Success<T, E extends Throwable> extends Try<T, E>
{
	private T value;

	@Override
	public boolean isSuccess()
	{
		return true;
	}

	@Override
	public boolean isFailure()
	{
		return false;
	}

	@Override
	public <R> Try<R, E> map(ThrowableFunction<T, R> function)
	{
		return Try.of(function, getValue());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <R> Try<R, E> flatMap(ThrowableFunction<T, Try<R, E>> function)
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

	@Override
	public Either<E, T> toEither()
	{
		return Right.of(getValue());
	}
}
