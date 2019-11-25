package htsign.util.trap;

import htsign.util.function.Consumer;
import htsign.util.function.Function;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * generally it express the failure.
 * @param <L>
 * @param <R>
 */
@AllArgsConstructor(staticName = "of")
@Value @EqualsAndHashCode(callSuper = true)
public class Left<L, R> extends Either<L, R>
{
	private L value;

	/** {@inheritDoc} */
	@Override
	public boolean isLeft()
	{
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isRight()
	{
		return false;
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public <T> Either<L, T> map(Function<? super R, ? extends T> function)
	{
		return (Either<L, T>) this;
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public <T> Either<L, T> flatMap(Function<? super R, ? extends Either<L, T>> function)
	{
		return (Either<L, T>) this;
	}

	/** {@inheritDoc} */
	@Override
	public void consume(Consumer<? super R> consumer) { }

	/** {@inheritDoc} */
	@Override
	public void match(Consumer<? super L> onLeft, Consumer<? super R> onRight) { }

	/** {@inheritDoc} */
	@Override
	public <T> T match(Function<? super L, ? extends T> onLeft, Function<? super R, ? extends T> onRight)
	{
		return onLeft.apply(getValue());
	}

	/** {@inheritDoc} */
	@Override
	public Try<R, RuntimeException> toTry()
	{
		return new Failure<R, RuntimeException>(new RuntimeException(String.valueOf(value)));
	}
}
