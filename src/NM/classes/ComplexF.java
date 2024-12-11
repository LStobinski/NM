package NM.classes;

public class ComplexF {
    final float real;
    final float imaginary;

    public ComplexF(float real, float imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public ComplexF add(ComplexF other) {
        return new ComplexF(this.real + other.real, this.imaginary + other.imaginary);
    }

    public ComplexF mul(ComplexF other) {
        return new ComplexF(
        this.real * other.real - this.imaginary * other.imaginary,
    other.real * other.imaginary + this.imaginary * other.real
        );
    }

    public String toString() {
        return String.format("{%.16f + %.16fi}", real, imaginary);
    }
}
