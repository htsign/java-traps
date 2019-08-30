package htsign.util.trap;

import htsign.util.function.Consumer;
import htsign.util.function.Function;

public abstract class Either<L, R>
{
	public abstract boolean isLeft();
	public abstract boolean isRight();
	public abstract <T> Either<L, T> map(Function<? super R, ? extends T> function);
	public abstract <T> Either<L, T> flatMap(Function<? super R, ? extends Either<L, T>> function);
	public abstract void consume(Consumer<? super R> consumer);
	public abstract void match(Consumer<? super L> onLeft, Consumer<? super R> onRight);
	public abstract <T> T match(Function<? super L, ? extends T> onLeft, Function<? super R, ? extends T> onRight);
	public abstract Try<R, RuntimeException> toTry();
}
