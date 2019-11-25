package htsign.util.trap;

import htsign.util.function.Consumer;
import htsign.util.function.Function;
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
	public <T> Either<L, T> map(Function<? super R, ? extends T> function)
	{
		return (Either<L, T>) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Either<L, T> flatMap(Function<? super R, ? extends Either<L, T>> function)
	{
		return (Either<L, T>) this;
	}

	@Override
	public void consume(Consumer<? super R> consumer) { }

	@Override
	public void match(Consumer<? super L> onLeft, Consumer<? super R> onRight) { }

	@Override
	public <T> T match(Function<? super L, ? extends T> onLeft, Function<? super R, ? extends T> onRight)
	{
		return onLeft.apply(getValue());
	}

	@Override
	public Try<R, RuntimeException> toTry()
	{
		return new Failure<R, RuntimeException>(new RuntimeException(String.valueOf(value)));
	}
}
