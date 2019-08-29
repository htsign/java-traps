package htsign.util.trap;

import htsign.util.function.Function;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;

@AllArgsConstructor(staticName = "of")
@Value @EqualsAndHashCode(callSuper = true)
public class Left<L, R> extends Either<L, R>
{
	private L value;

	@Override
	public boolean isLeft()
	{
		return true;
	}

	@Override
	public boolean isRight()
	{
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Either<L, T> map(Function<R, T> function)
	{
		return (Either<L, T>) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Either<L, T> flatMap(Function<R, Either<L, T>> function)
	{
		return (Either<L, T>) this;
	}

	@Override
	public R match(Function<L, R> onLeft, Function<R, R> onRight)
	{
		return onLeft.apply(getValue());
	}

	@Override
	public Try<R, RuntimeException> toTry()
	{
		return new Failure<R, RuntimeException>(new RuntimeException(String.valueOf(value)));
	}
}
