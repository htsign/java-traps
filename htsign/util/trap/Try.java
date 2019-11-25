package htsign.util.trap;

import htsign.util.function.Consumer;
import htsign.util.function.Function;
import htsign.util.function.ThrowableFunction;
import htsign.util.function.ThrowableSupplier;

/**
 * this is monad like class.
 * @param <T> succeeded object type
 * @param <E> failed exception type
 * @see <a href="https://www.scala-lang.org/api/2.13.0/scala/util/Try.html">Scala Standard Library 2.13.0 - scala.util.Try</a>
 */
public abstract class Try<T, E extends Throwable>
{
	/**
	 * return {@code true} if this is {@link Success}.
	 */
	public abstract boolean isSuccess();

	/**
	 * return {@code true} if this is {@link Failure}.
	 */
	public abstract boolean isFailure();

	/**
	 * change the value with mapping {@code function} if it is {@link Success},
	 * but is {@link Failure} do nothing.
	 * @param function mapping function
	 * @param <R> new value type
	 * @return new {@link Try} object has mapped value
	 */
	public abstract <R> Try<R, E> map(ThrowableFunction<? super T, ? extends R, E> function);

	/**
	 * change the value with mapping {@code function} if it is {@link Success},
	 * but is {@link Failure} then do nothing. <br/>
	 * this method is similar of {@link Try#map(ThrowableFunction)},
	 * but this required a function returns new {@link Try} object
	 * @param function mapping function returns new {@link Try} object
	 * @param <R> new value type
	 * @return new {@link Try} object has mapped value
	 */
	public abstract <R> Try<R, E> flatMap(ThrowableFunction<? super T, ? extends Try<R, E>, E> function);

	/**
	 * consume a value if this is {@link Success}, otherwise do nothing.
	 * @param consumer consumer
	 */
	public abstract void consume(Consumer<? super T> consumer);

	/**
	 * consume value with functions, {@code onFailure} is used if this is {@link Failure}, vise versa.
	 * @param onFailure function to execute if this is {@link Failure}
	 * @param onSuccess function to execute if this is {@link Success}
	 */
	public abstract void match(Consumer<? super E> onFailure, Consumer<? super T> onSuccess);

	/**
	 * map value with functions. {@code onFailure} is used if this is {@link Failure}, vise versa.
	 * @param onFailure function to execute if this is {@link Failure}
	 * @param onSuccess function to execute if this is {@link Success}
	 * @param <U> returned value type
	 * @return mapped value
	 */
	public abstract <U> U match(Function<? super E, ? extends U> onFailure, Function<? super T, ? extends U> onSuccess);

	/**
	 * convert to {@link Either} object.
	 * <ul>
	 *   <li>to the {@link Left} of {@link E} if this is {@link Failure}</li>
	 *   <li>to the {@link Right} of {@link T} if this is {@link Success}</li>
	 * </ul>
	 * @return new {@link Either} object
	 */
	public abstract Either<E, T> toEither();

	/**
	 * create new {@link Try} object with {@link ThrowableSupplier}. <br/>
	 * returns {@link Failure} if {@code supplier} occurred an error. if not, returns {@link Success}.
	 * @param supplier function that may fail
	 * @param <R> returned value type
	 * @param <E> exception type
	 * @return new {@link Try} object
	 */
	@SuppressWarnings("unchecked")
	public static <R, E extends Throwable> Try<R, E> of(ThrowableSupplier<R, E> supplier)
	{
		try
		{
			return new Success<R, E>(supplier.getThrows());
		}
		catch (Throwable throwable)
		{
			return new Failure<R, E>((E) throwable);
		}
	}

	/**
	 * create new {@link Try} object with {@link ThrowableFunction}. <br/>
	 * returns {@link Failure} if {@code supplier} occurred an error. if not, returns {@link Success}.
	 * @param function function that may fail
	 * @param arg argument value of {@code function}
	 * @param <T> type of {@code arg}
	 * @param <R> returned value type
	 * @param <E> exception type
	 * @return new {@link Try} object
	 */
	@SuppressWarnings("unchecked")
	public static <T, R, E extends Throwable> Try<R, E> of(
		ThrowableFunction<? super T, ? extends R, E> function,
		T arg
	) {
		try
		{
			return new Success<R, E>(function.applyThrows(arg));
		}
		catch (Throwable throwable)
		{
			return new Failure<R, E>((E) throwable);
		}
	}
}
	{
		try
		{
			return new Success<R, E>(supplier.getThrows());
		}
		catch (Throwable throwable)
		{
			return new Failure<R, E>((E) throwable);
		}
	}

	/**
	 * create new {@link Try} object with {@link ThrowableFunction}. <br/>
	 * returns {@link Failure} if {@code supplier} occurred an error. if not, returns {@link Success}.
	 * @param function function that may fail
	 * @param arg argument value of {@code function}
	 * @param <T> type of {@code arg}
	 * @param <R> returned value type
	 * @param <E> exception type
	 * @return new {@link Try} object
	 */
	@SuppressWarnings("unchecked")
	public static <T, R, E extends Throwable> Try<R, E> of(
		ThrowableFunction<? super T, ? extends R, E> function,
		T arg
	) {
		try
		{
			return new Success<R, E>(function.applyThrows(arg));
		}
		catch (Throwable throwable)
		{
			return new Failure<R, E>((E) throwable);
		}
	}
}
