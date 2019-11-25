package htsign.util.trap;

import htsign.util.function.Consumer;
import htsign.util.function.Function;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;

@AllArgsConstructor(staticName = "of")
@Value @EqualsAndHashCode(callSuper = true)
public class Right<L, R> extends Either<L, R>
{
	private R value;

	/** {@inheritDoc} */
	@Override
	public boolean isLeft()
	{
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isRight()
	{
		return true;
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public <T> Either<L, T> map(Function<? super R, ? extends T> function)
	{
		return (Either<L, T>) of(function.apply(getValue()));
	}

	/** {@inheritDoc} */
	@Override
	public <T> Either<L, T> flatMap(Function<? super R, ? extends Either<L, T>> function)
	{
		return function.apply(getValue());
	}

	/** {@inheritDoc} */
	@Override
	public void consume(Consumer<? super R> consumer)
	{
		consumer.accept(getValue());
	}

	/** {@inheritDoc} */
	@Override
	public void match(Consumer<? super L> onLeft, Consumer<? super R> onRight)
	{
		onRight.accept(getValue());
	}

	/** {@inheritDoc} */
	@Override
	public <T> T match(Function<? super L, ? extends T> onLeft, Function<? super R, ? extends T> onRight)
	{
		return onRight.apply(getValue());
	}

	/** {@inheritDoc} */
	@Override
	public Try<R, RuntimeException> toTry()
	{
		return new Success<R, RuntimeException>(value);
	}
}
