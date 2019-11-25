package htsign.util.trap;

import htsign.util.function.Consumer;
import htsign.util.function.Function;

/**
 * this is monad like class.
 * @param <L> failed object type
 * @param <R> succeeded object type
 * @see <a href="https://www.scala-lang.org/api/2.13.0/scala/util/Either.html">Scala Standard Library 2.13.0 - scala.util.Either</a>
 */
public abstract class Either<L, R>
{
	/**
	 * return {@code true} if this is {@link Left}.
	 */
	public abstract boolean isLeft();

	/**
	 * return {@code true} if this is {@link Right}.
	 */
	public abstract boolean isRight();

	/**
	 * change the value with mapping {@code function} if it is {@link Right},
	 * but is {@link Left} do nothing.
	 * @param function mapping function
	 * @param <T> new right value type
	 * @return new {@link Either} object has mapped value
	 */
	public abstract <T> Either<L, T> map(Function<? super R, ? extends T> function);

	/**
	 * change the value with mapping {@code function} if it is {@link Right},
	 * but is {@link Left} then do nothing. <br/>
	 * this method is similar of {@link Either#map(Function)},
	 * but this required a function return new {@link Either} object.
	 * @param function mapping function returns new {@link Either} object
	 * @param <T> new right value type
	 * @return new {@link Either} object has mapped value
	 */
	public abstract <T> Either<L, T> flatMap(Function<? super R, ? extends Either<L, T>> function);

	/**
	 * consume right value if this is {@link Right}, otherwise do nothing.
	 * @param consumer consumer
	 */
	public abstract void consume(Consumer<? super R> consumer);

	/**
	 * consume value with functions. {@code onLeft} is used if this is {@link Left}, vise versa.
	 * @param onLeft function to execute if this is {@link Left}
	 * @param onRight function to execute if this is {@link Right}
	 */
	public abstract void match(Consumer<? super L> onLeft, Consumer<? super R> onRight);

	/**
	 * map value with functions. {@code onLeft} is used if this is {@link Left}, vise versa.
	 * @param onLeft function to execute if this is {@link Left}
	 * @param onRight function to execute if this is {@link Right}
	 * @param <T> returned value type
	 * @return mapped value
	 */
	public abstract <T> T match(Function<? super L, ? extends T> onLeft, Function<? super R, ? extends T> onRight);

	/**
	 * convert to {@link Try} object.
	 * <ul>
	 *   <li>to the {@link Failure} of {@link RuntimeException} has {@link L} value if this is {@link Left}.</li>
	 *   <li>to the {@link Success} of {@link R} if this is {@link Right}</li>
	 * @return new {@link Try} object
	 */
	public abstract Try<R, RuntimeException> toTry();
}
