package org.beecrypt

import java.lang.RuntimeException

/**
 * (c) 2011 ...
 *
 * User: zvozin
 * Date: 6/19/11
 * Time: 12:36 PM
 */
// Copyright (c) 2011 Alex Zuzin <carnatus@gmail.com>
//
// Permission to use, copy, modify, and distribute this software for any
// purpose with or without fee is hereby granted, provided that the above
// copyright notice and this permission notice appear in all copies.
//
// THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
// WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
// ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
// WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
// ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
// OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.

/**
 * Making BCrypt look prettier
 */
trait BeeCrypt {

  import java.security.SecureRandom

  /**
   * For readability.
   */
  type PlainPassword = String
  type Salt = String
  type PasswordHash = String

  /**
   * Useful salting default
   */
  val QuickAndWeak = 6
  val GoodEnough = 10
  val RatherTough = 14
  val PrettyInsane = 18

  /**
   * For Scala-bility
   */
  type SaltGenerator = (Int) => Salt
  private val random: SecureRandom = new SecureRandom
  private val defaultSaltGenerator: SaltGenerator = (rounds: Int) => BCrypt.gensalt(rounds, random)

  /**
   * And for the actual API
   *
   * @params rounds - this is the base of log2 of the number of salting rounds. Legal values are >=4, sane values are <=20
   */
  private val roundsError = "As the number of operations grows with 2^rounds, legal/sane values of rounds are in fixed in (4..20). Smaller is less secure, larger is significantly slower"

  def hashPassword(password: PlainPassword,
                   rounds: Int = goodEnough,
                   saltGenerator: SaltGenerator = defaultSaltGenerator): PasswordHash = {

    def roundCountLegal(rounds: Int) = rounds >= 4 && rounds <= 20

    if (!roundCountLegal(rounds)) throw new RuntimeException(roundsError)

    BCrypt.hashpw(password, saltGenerator(rounds))
  }

  def matches(plainTextPassword: PlainPassword,
              hashedPassword: PasswordHash): Boolean = hashedPassword.compareTo(BCrypt.hashpw(plainTextPassword, hashedPassword)) == 0
}

object BeeCrypt extends BeeCrypt
