<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

/**
 * App\Models\Buku
 *
 * @property int $id
 * @property string|null $judul
 * @method static \Illuminate\Database\Eloquent\Builder|Buku newModelQuery()
 * @method static \Illuminate\Database\Eloquent\Builder|Buku newQuery()
 * @method static \Illuminate\Database\Eloquent\Builder|Buku query()
 * @method static \Illuminate\Database\Eloquent\Builder|Buku whereId($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Buku whereJudul($value)
 * @mixin \Eloquent
 */
class Buku extends Model
{
    use HasFactory;
    protected $table = 'buku';
    public $timestamps = false;
    protected $guarded = [];

    public function findByJudul($judul)
    {
        return $this->whereRaw("UPPER(judul) LIKE '%" . strtoupper($judul) . "%'")->get();
    }
}
