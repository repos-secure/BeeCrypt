package org.beecrypt

import sun.security.util.Password

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
   * For Scala-bility
   */
  type SaltGenerator = () => Salt
  type SecureRandomProvider = () => SecureRandom

  /**
   * Userful defaults
   */
  private val random: SecureRandom = new SecureRandom
  private val defaultSaltingRounds: Int = 10

  private val defaultSecureRandomProvider = () => random
  private val defaultSaltGenerator = () => generateSalt()

  /**
   * And for the actual API
   */
  def generateSalt(rounds: Int = defaultSaltingRounds,
                   secureRandomProvider: SecureRandomProvider = defaultSecureRandomProvider): Salt = BCrypt.gensalt(rounds, secureRandomProvider())

  def hashPassword(password: PlainPassword,
                   saltGenerator: SaltGenerator = defaultSaltGenerator): PasswordHash = BCrypt.hashpw(password, saltGenerator())

  def matches(plainTextPassword: PlainPassword,
              hashedPassword: PasswordHash): Boolean = hashedPassword.compareTo(hashPassword(plainTextPassword, () => hashedPassword)) == 0
}

object BeeCrypt extends BeeCrypt
