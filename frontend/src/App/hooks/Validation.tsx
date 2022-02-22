
export const useValidationUsername = () => ({username}: { username: string }): void => {
    username = username.trim()
    if (username.length === 0) throw new Error("Nombre de usuario vacío")
    if (!/^[a-z]/.test(username)) throw new Error("Nombre de usuario no valido")
}

export const useValidationPassword = () => ({password}: { password: string }): void => {
    password = password.trim()
    if (password.length === 0) throw new Error("Contraseña vacía")
    if (!/^[a-zA-z0-9]/.test(password)) throw new Error("Contraseña no valido")
}