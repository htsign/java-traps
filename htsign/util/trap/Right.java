package htsign.util.trap;

import htsign.util.function.Consumer;
import htsign.util.function.Function;
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

	@SuppressWarnings("unchecked")
	@Override
	public <T> Either<L, T> map(Function<? super R, ? extends T> function)
	{
		return (Either<L, T>) of(function.apply(getValue()));
	}

	@Override
	public <T> Either<L, T> flatMap(Function<? super R, ? extends Either<L, T>> function)
	{
		return function.apply(getValue());
	}

	@Override
	public void consume(Consumer<? super R> consumer)
	{
		consumer.accept(getValue());
	}

	@Override
	public void match(Consumer<? super L> onLeft, Consumer<? super R> onRight)
	{
		onRight.accept(getValue());
	}

	@Override
	public <T> T match(Function<? super L, ? extends T> onLeft, Function<? super R, ? extends T> onRight)
	{
		return onRight.apply(getValue());
	}

	@Override
	public Try<R, RuntimeException> toTry()
	{
		return new Success<R, RuntimeException>(value);
	}
}
