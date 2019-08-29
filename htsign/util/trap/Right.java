package htsign.util.trap;

import htsign.util.function.Function;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;

@AllArgsConstructor(staticName = "of")
@Value
@EqualsAndHashCode(callSuper = true)
public class Right<L, R> extends Either<L, R>
{
	private R value;

	@Override
	public boolean isLeft()
	{
		return false;
	}

	@Override
	public boolean isRight()
	{
		return true;
	}

	@Override
	public <T> Either<L, T> map(Function<R, T> function)
	{
		return of(function.apply(getValue()));
	}

	@Override
	public <T> Either<L, T> flatMap(Function<R, Either<L, T>> function)
	{
		return function.apply(getValue());
	}

	@Override
	public R match(Function<L, R> onLeft, Function<R, R> onRight)
	{
		return onRight.apply(getValue());
	}

	@Override
	public Try<R, RuntimeException> toTry()
	{
		return new Success<R, RuntimeException>(value);
	}
}
