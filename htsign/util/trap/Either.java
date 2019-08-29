package htsign.util.trap;

import htsign.util.function.Consumer;
import htsign.util.function.Function;

public abstract class Either<L, R>
{
	public abstract boolean isLeft();
	public abstract boolean isRight();
	public abstract <T> Either<L, T> map(Function<R, T> function);
	public abstract <T> Either<L, T> flatMap(Function<R, Either<L, T>> function);
	public abstract void match(Consumer<L> onLeft, Consumer<R> onRight);
	public abstract <U> U match(Function<L, R> onLeft, Function<R, U> onRight);
	public abstract Try<R, RuntimeException> toTry();
}
